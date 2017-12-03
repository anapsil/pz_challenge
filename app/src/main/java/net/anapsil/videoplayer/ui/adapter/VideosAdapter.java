package net.anapsil.videoplayer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.anapsil.videoplayer.R;
import net.anapsil.videoplayer.model.Content;

import java.util.Collections;
import java.util.List;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosViewHolder> {
    private List<Content> objects;

    public VideosAdapter() {
        objects = Collections.emptyList();
    }

    public VideosAdapter(List<Content> objects) {
        this.objects = objects;
    }

    public List<Content> getObjects() {
        return objects;
    }

    public void setObjects(List<Content> objects) {
        this.objects = objects;
        notifyDataSetChanged();
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
        holder.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
}
