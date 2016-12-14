package com.example.david.twitterclient.images.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.twitterclient.R;
import com.example.david.twitterclient.entities.Image;
import com.example.david.twitterclient.lib.base.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by david on 14/12/16.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private List<Image> imageList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public ImagesAdapter(List<Image> imageList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.imageList = imageList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_images, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image image = imageList.get(position);
        holder.setOnClickListener(image, onItemClickListener);
        holder.txtTweet.setText(image.getTweetText());
        imageLoader.load(holder.imageMedia, image.getTweeetUrl());
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    private void setItems(List<Image> newItems){
        imageList.addAll(newItems);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtTweet)
        TextView txtTweet;

        @BindView(R.id.imgMedia)
        ImageView imageMedia;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public void setOnClickListener(final Image image, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(image);
                }
            });
        }
    }

}
