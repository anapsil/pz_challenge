package net.anapsil.videoplayer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.anapsil.videoplayer.R;
import net.anapsil.videoplayer.model.Content;

import java.util.ArrayList;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosViewHolder> {
    private ArrayList<Content> objects;
    private VideosItemViewModel.OnDownloadClickListerner listener;

    public VideosAdapter(VideosItemViewModel.OnDownloadClickListerner listener) {
        objects = new ArrayList<>();
        this.listener = listener;
    }

    public ArrayList<Content> getObjects() {
        return objects;
    }

    public void addItem(Content content) {
        objects.add(content);
        notifyItemInserted(objects.size() - 1);
    }

    @Override
    public VideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_videos, parent, false);
        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideosViewHolder holder, int position) {
        holder.getViewModel().update(objects.get(position));
        holder.getViewModel().setAdapter(this);
        holder.getViewModel().setPosition(position);
        holder.getViewModel().setOnDownloadClickListerner(listener);
        holder.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
}
