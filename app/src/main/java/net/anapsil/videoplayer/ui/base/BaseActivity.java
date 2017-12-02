package net.anapsil.videoplayer.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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
        performDataBinding();
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        viewModel.onViewCreated();
    }

    @Override
    protected void onDestroy() {
        viewModel.onDestroyView();
        super.onDestroy();
    }

    private void performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = getViewModel();
        binding.setVariable(BR.vm, viewModel);
        binding.executePendingBindings();
    }

    public B getBinding() {
        return binding;
    }

    protected abstract int getLayoutId();

    protected abstract VM getViewModel();
}