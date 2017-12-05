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
    boolean isList = false;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(getBinding().toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getBinding().videosList.setHasFixedSize(true);
        getBinding().videosList.setAdapter(getViewModel().getAdapter());
    }

    @Override
    protected void createViewModel() {
        viewModel = new MainViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getViewModel() {
        return viewModel;
    }
}
