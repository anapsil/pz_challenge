package net.anapsil.videoplayer.ui;

import android.Manifest;
import android.content.Context;
import android.util.Log;

import net.anapsil.videoplayer.business.AssetsBusiness;
import net.anapsil.videoplayer.model.Content;
import net.anapsil.videoplayer.remote.MediaServiceGenerator;
import net.anapsil.videoplayer.repository.AssetsRepo;
import net.anapsil.videoplayer.ui.adapter.VideosAdapter;
import net.anapsil.videoplayer.ui.adapter.VideosItemViewModel;
import net.anapsil.videoplayer.ui.base.viewmodel.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class MainViewModel extends BaseViewModel implements VideosItemViewModel.OnDownloadClickListener {
    private static final int RC_WRITE_STORAGE = 123;
    private Context context;
    private VideosAdapter adapter;
    private AssetsBusiness business;

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
    public void onDownloadClicked(Content contentToDownload, int position) {
        downloadContent(contentToDownload, position);
    }

    @AfterPermissionGranted(RC_WRITE_STORAGE)
    public void downloadContent(Content content, final int position) {
        if (EasyPermissions.hasPermissions(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.d(getClass().getName(), "Permission granted, starting download...");
            compositeDisposable.add(business.download(content.getBg())
                    .concatWith(business.download(content.getSg()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        Log.d(getClass().getName(), "Download Completed");
                        adapter.notifyDownloadCompleted(position);
                    }, Throwable::printStackTrace));
        } else {
            EasyPermissions.requestPermissions((MainActivity) context, "", RC_WRITE_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }
}
