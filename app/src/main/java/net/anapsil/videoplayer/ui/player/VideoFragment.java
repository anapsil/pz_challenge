package net.anapsil.videoplayer.ui.player;

import android.os.Bundle;

import net.anapsil.videoplayer.R;
import net.anapsil.videoplayer.databinding.FragmentVideoBinding;
import net.anapsil.videoplayer.model.Content;
import net.anapsil.videoplayer.ui.base.BaseFragment;

import org.parceler.Parcels;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class VideoFragment extends BaseFragment<FragmentVideoBinding, VideoViewModel> {

    public static VideoFragment newInstance(Content content) {
        Bundle args = new Bundle();
        args.putParcelable("ARGS", Parcels.wrap(content));
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void init() {
        Content content = Parcels.unwrap(getArguments().getParcelable("ARGS"));
        getActivity().setTitle(content.getName());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected VideoViewModel getViewModel() {
        return new VideoViewModel();
    }
}
