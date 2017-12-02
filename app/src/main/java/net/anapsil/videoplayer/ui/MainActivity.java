package net.anapsil.videoplayer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.anapsil.videoplayer.R;
import net.anapsil.videoplayer.databinding.ActivityMainBinding;
import net.anapsil.videoplayer.ui.base.BaseActivity;

/**
 * Created by anapsil on 02/12/17.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(getBinding().toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getBinding().videosList.setHasFixedSize(true);
        getBinding().videosList.setAdapter(getViewModel().getAdapter());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getViewModel() {
        return new MainViewModel(this);
    }
}
