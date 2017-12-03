package net.anapsil.videoplayer.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.anapsil.videoplayer.BR;
import net.anapsil.videoplayer.ui.base.viewmodel.BaseViewModel;

/**
 * @author anapsil
 * @since 1.0.0
 */

public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    private B binding;
    private VM viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = getViewModel();
        binding.setVariable(BR.vm, viewModel);
        binding.executePendingBindings();
        viewModel.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        viewModel.onDestroyView();
        super.onDestroyView();
    }

    protected B getBinding() {
        return binding;
    }

    protected abstract int getLayoutId();

    protected abstract VM getViewModel();
}
