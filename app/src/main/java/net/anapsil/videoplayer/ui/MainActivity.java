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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(getBinding().toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getBinding().videosList.setHasFixedSize(true);
        getBinding().videosList.setAdapter(getViewModel().getAdapter());

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_change:
//                if (!isList) {
//                    ((GridLayoutManager) getBinding().videosList.getLayoutManager()).setSpanCount(1);
//                    item.setIcon(R.drawable.ic_grid);
//                } else {
//                    int spanCount;
//                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                        spanCount = 3;
//                    } else {
//                        spanCount = 2;
//                    }
//                    ((GridLayoutManager) getBinding().videosList.getLayoutManager()).setSpanCount(spanCount);
//                    item.setIcon(R.drawable.ic_list);
//                }
//                isList = !isList;
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getViewModel() {
        return new MainViewModel(this);
    }
}
