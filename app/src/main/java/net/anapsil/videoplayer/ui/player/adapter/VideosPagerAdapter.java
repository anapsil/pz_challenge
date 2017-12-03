package net.anapsil.videoplayer.ui.player.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.anapsil.videoplayer.model.Content;
import net.anapsil.videoplayer.ui.player.VideoFragment;

import java.util.List;

/**
 * Created by anapsil on 02/12/17.
 */

public class VideosPagerAdapter extends FragmentStatePagerAdapter {

    private List<Content> contentList;

    public VideosPagerAdapter(FragmentManager fm, List<Content> contentList) {
        super(fm);
        this.contentList = contentList;
    }

    @Override
    public Fragment getItem(int position) {
        return VideoFragment.newInstance(contentList.get(position));
    }

    @Override
    public int getCount() {
        return contentList.size();
    }
}
