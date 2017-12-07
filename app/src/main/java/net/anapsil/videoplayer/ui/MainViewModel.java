package net.anapsil.videoplayer.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import net.anapsil.videoplayer.R;
import net.anapsil.videoplayer.VideoPlayerApplication;
import net.anapsil.videoplayer.business.AssetsBusiness;
import net.anapsil.videoplayer.model.Content;
import net.anapsil.videoplayer.remote.MediaServiceGenerator;
import net.anapsil.videoplayer.repository.AssetsRepo;
import net.anapsil.videoplayer.ui.adapter.VideosAdapter;
import net.anapsil.videoplayer.ui.adapter.VideosItemViewModel;
import net.anapsil.videoplayer.ui.base.viewmodel.BaseViewModel;
import net.anapsil.videoplayer.ui.player.PlayerActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class MainViewModel extends BaseViewModel implements VideosItemViewModel.OnDownloadClickListener {
    public static final int RC_WRITE_STORAGE = 123;
    private Context context;
    private VideosAdapter adapter;
    private AssetsBusiness business;
    private Content contentToDownload;
    private int position;

    public MainViewModel(Context context) {
        this.context = context;
        business = new AssetsBusiness(new AssetsRepo(MediaServiceGenerator.with().service()));
        adapter = new VideosAdapter(this);
    }

    public VideosAdapter getAdapter() {
        return adapter;
    }

    @Override
    protected void loadData() {
        compositeDisposable.add(business.getContents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(content -> adapter.addItem(content), Throwable::printStackTrace));
    }

    @Override
    public void onDownloadClicked(final Content content, final int position) {
        this.contentToDownload = content;
        this.position = position;
        downloadContent();
    }

    @Override
    public void onDestroyView() {
        VideoPlayerApplication.saveDownloadedContent();
        super.onDestroyView();
    }

    public void downloadContent() {
        if (EasyPermissions.hasPermissions(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.d(getClass().getName(), "Permission granted, starting download: " + contentToDownload.getName());
            adapter.notifyDownloadStarted(position);
            compositeDisposable.add(business.download(contentToDownload.getBg())
                    .concatWith(business.download(contentToDownload.getSg()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        business.addDownloadedContent(contentToDownload);
                        adapter.notifyDownloadCompleted(position);
                        Log.d(getClass().getName(), "Download Completed");
                        openPlayer();
                    }, Throwable::printStackTrace));
        } else {
            EasyPermissions.requestPermissions((MainActivity) context,
                    context.getString(R.string.rationale_storage),
                    RC_WRITE_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    private void openPlayer() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ARGS", adapter.getObjects());
        bundle.putInt("ARGS_POSITION", position);
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
