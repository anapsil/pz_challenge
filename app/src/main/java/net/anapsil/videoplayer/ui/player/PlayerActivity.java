package net.anapsil.videoplayer.ui.player;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.anapsil.videoplayer.R;
import net.anapsil.videoplayer.databinding.ActivityPlayerBinding;
import net.anapsil.videoplayer.ui.base.BaseActivity;

/**
 * @author anapsil
 * @since 1.0
 */

public class PlayerActivity extends BaseActivity<ActivityPlayerBinding, PlayerViewModel> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(getBinding().toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_player;
    }

    @Override
    protected PlayerViewModel getViewModel() {
        return new PlayerViewModel();
    }
}
