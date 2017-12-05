package net.anapsil.videoplayer.ui;

import android.content.Context;

import net.anapsil.videoplayer.business.AssetsBusiness;
import net.anapsil.videoplayer.remote.MediaServiceGenerator;
import net.anapsil.videoplayer.repository.AssetsRepo;
import net.anapsil.videoplayer.ui.adapter.VideosAdapter;
import net.anapsil.videoplayer.ui.base.viewmodel.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class MainViewModel extends BaseViewModel {
    private Context context;
    private VideosAdapter adapter;
    private AssetsBusiness business;

    public MainViewModel(Context context) {
        this.context = context;
        business = new AssetsBusiness(new AssetsRepo(MediaServiceGenerator.with().service()));
        adapter = new VideosAdapter();
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
//        Gson gson = new Gson();
//        Assets assets = gson.fromJson(IOHelper.loadJSONFromAsset(context, "assets.json"), Assets.class);
//        VideoPlayerApplication.setAssetsLocation(assets.getAssetsLocation());
//        adapter = new VideosAdapter(assets.getObjects());
//        adapter.setObjects(assets.getObjects());
//        adapter.notifyDataSetChanged();
    }
}
