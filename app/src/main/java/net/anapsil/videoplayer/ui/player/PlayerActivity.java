package net.anapsil.videoplayer.ui.player;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.anapsil.videoplayer.R;
import net.anapsil.videoplayer.VideoPlayerApplication;
import net.anapsil.videoplayer.databinding.ActivityPlayerBinding;
import net.anapsil.videoplayer.model.Content;
import net.anapsil.videoplayer.ui.base.BaseActivity;

import java.util.List;

/**
 * @author anapsil
 * @since 1.0
 */

public class PlayerActivity extends BaseActivity<ActivityPlayerBinding, PlayerViewModel> {
    private List<Content> contentList;
    private int selectedItem;
    private PlayerViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(getBinding().toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    @Override
    protected void createViewModel() {
        viewModel = new PlayerViewModel(this);
    }

    private void init() {
        contentList = getIntent().getParcelableArrayListExtra("ARGS");
        selectedItem = getIntent().getIntExtra("ARGS_POSITION", 0);
        getBinding().includedView.player.requestFocus();
        String videoUrl = String.format("%s/%s", VideoPlayerApplication.getAssetsLocation(), contentList.get(selectedItem).getBg());
        String audioUrl = String.format("%s/%s", VideoPlayerApplication.getAssetsLocation(), contentList.get(selectedItem).getSg());
        getViewModel().prepareAudioPlayer(audioUrl);
        getViewModel().prepareVideoPlayer(videoUrl);

        getBinding().includedView.player.setPlayer(getViewModel().getVideoPlayer());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_player;
    }

    @Override
    protected PlayerViewModel getViewModel() {
        return viewModel;
    }
}
