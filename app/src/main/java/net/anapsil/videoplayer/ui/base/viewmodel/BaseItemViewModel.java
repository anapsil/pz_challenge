package net.anapsil.videoplayer.ui.base.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

/**
 * @author anapsil
 * @since 1.0.0
 */

public abstract class BaseItemViewModel<I extends Object> extends BaseObservable {
    protected I item;

    public void update(I item) {
        this.item = item;
        setProperties();
        notifyChange();
    }

    public abstract void onItemClick(View v);

    protected abstract void setProperties();
}
