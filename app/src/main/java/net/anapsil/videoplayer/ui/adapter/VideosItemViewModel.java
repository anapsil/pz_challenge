package net.anapsil.videoplayer.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.anapsil.videoplayer.VideoPlayerApplication;
import net.anapsil.videoplayer.model.Content;
import net.anapsil.videoplayer.ui.base.viewmodel.BaseItemViewModel;
import net.anapsil.videoplayer.ui.binding.ObservableString;
import net.anapsil.videoplayer.ui.player.PlayerActivity;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class VideosItemViewModel extends BaseItemViewModel<Content> {
    public ObservableString image = new ObservableString();
    public ObservableString name = new ObservableString();

    private int position;
    private VideosAdapter adapter;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public VideosAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(VideosAdapter adapter) {
        this.adapter = adapter;
    }

    public void onItemClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ARGS", adapter.getObjects());
        bundle.putInt("ARGS_POSITION", position);
        Intent intent = new Intent(v.getContext(), PlayerActivity.class);
        intent.putExtras(bundle);
        v.getContext().startActivity(intent);
    }

    @Override
    protected void setProperties() {
        image.set(String.format("%s/%s", VideoPlayerApplication.getAssetsLocation(), item.getIm()));
        name.set(item.getName().split(" - ")[1]);
    }
}
