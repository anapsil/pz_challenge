package net.anapsil.videoplayer.ui.adapter;

import android.view.View;

import net.anapsil.videoplayer.databinding.ItemVideosBinding;
import net.anapsil.videoplayer.ui.base.BaseViewHolder;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class VideosViewHolder extends BaseViewHolder<ItemVideosBinding, VideosItemViewModel> {
    private VideosItemViewModel videosItemViewModel;


    public VideosViewHolder(View itemView) {
        super(itemView);
        videosItemViewModel = new VideosItemViewModel();
        bindContent(itemView);
    }


    @Override
    public VideosItemViewModel getViewModel() {
        return videosItemViewModel;
    }
}
