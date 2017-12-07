package net.anapsil.videoplayer.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.anapsil.videoplayer.R;
import net.anapsil.videoplayer.databinding.ActivityMainBinding;
import net.anapsil.videoplayer.ui.base.BaseActivity;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
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

    @AfterPermissionGranted(MainViewModel.RC_WRITE_STORAGE)
    public void download() {
        getViewModel().downloadContent();
    }
}
