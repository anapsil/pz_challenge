package net.anapsil.videoplayer.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.anapsil.videoplayer.BR;
import net.anapsil.videoplayer.ui.base.viewmodel.BaseViewModel;

/**
 * @author anapsil
 * @since 1.0
 */

public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
    private B binding;
    private VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModel();
        performDataBinding();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewModel.onViewCreated();
    }

    @Override
    protected void onStop() {
        viewModel.onDestroyView();
        super.onStop();
    }

    private void performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = getViewModel();
        binding.setVariable(BR.vm, viewModel);
        binding.executePendingBindings();
    }

    protected abstract void createViewModel();

    public B getBinding() {
        return binding;
    }

    protected abstract int getLayoutId();

    protected abstract VM getViewModel();
}