package net.anapsil.videoplayer.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import net.anapsil.videoplayer.VideoPlayerApplication;
import net.anapsil.videoplayer.model.Content;
import net.anapsil.videoplayer.ui.base.viewmodel.BaseItemViewModel;
import net.anapsil.videoplayer.ui.binding.ObservableString;
import net.anapsil.videoplayer.ui.player.PlayerActivity;

import org.parceler.Parcels;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class VideosItemViewModel extends BaseItemViewModel<Content> {
    public ObservableString image = new ObservableString();
    public ObservableString name = new ObservableString();

    private VideosAdapter adapter;

    public VideosAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(VideosAdapter adapter) {
        this.adapter = adapter;
    }

    public void onItemClick(View v) {
        Toast.makeText(v.getContext(), item.getName(), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ARGS", Parcels.wrap(adapter.getObjects()));
        v.getContext().startActivity(new Intent(v.getContext(), PlayerActivity.class), bundle);
    }

    @Override
    protected void setProperties() {
        image.set(String.format("%s/%s", VideoPlayerApplication.getAssetsLocation(), item.getIm()));
        name.set(item.getName().split(" - ")[1]);
    }
}
