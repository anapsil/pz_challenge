package net.anapsil.videoplayer.ui.base.viewmodel;

import android.databinding.BaseObservable;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author anapsil
 * @since 1.0
 */

public abstract class BaseViewModel extends BaseObservable {
    protected CompositeDisposable compositeDisposable;

    public void onViewCreated() {
        compositeDisposable = new CompositeDisposable();
        loadData();
    }

    public void onDestroyView() {
        compositeDisposable.dispose();
    }

    protected abstract void loadData();
}
