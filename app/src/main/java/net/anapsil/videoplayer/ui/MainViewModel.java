package net.anapsil.videoplayer.ui;

import android.content.Context;

import com.google.gson.Gson;

import net.anapsil.videoplayer.VideoPlayerApplication;
import net.anapsil.videoplayer.model.Assets;
import net.anapsil.videoplayer.ui.adapter.VideosAdapter;
import net.anapsil.videoplayer.ui.base.viewmodel.BaseViewModel;
import net.anapsil.videoplayer.util.IOHelper;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class MainViewModel extends BaseViewModel {
    private Context context;
    private VideosAdapter adapter;

    public MainViewModel(Context context) {
        this.context = context;
        loadData();
    }

    public VideosAdapter getAdapter() {
        return adapter;
    }

    @Override
    protected void loadData() {
        Gson gson = new Gson();
        Assets assets = gson.fromJson(IOHelper.loadJSONFromAsset(context, "assets.json"), Assets.class);
        VideoPlayerApplication.setAssetsLocation(assets.getAssetsLocation());
        adapter = new VideosAdapter(assets.getObjects());
//        adapter.setObjects(assets.getObjects());
//        adapter.notifyDataSetChanged();
    }
}
