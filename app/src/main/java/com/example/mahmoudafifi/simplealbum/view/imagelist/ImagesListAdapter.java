package com.example.mahmoudafifi.simplealbum.view.imagelist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mahmoudafifi.simplealbum.R;
import com.example.mahmoudafifi.simplealbum.model.Image;
import com.example.mahmoudafifi.simplealbum.view.singleimage.SingleImageFragment;

import java.util.List;

public class ImagesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Image> images;
    private Context context;

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView photoGridImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            photoGridImageView = itemView.findViewById(R.id.photoGridImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public ImagesListAdapter(List<Image> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.photos_grid_item, viewGroup, false);
        return new ImagesListAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        setItemWidthAndHeight(holder);
        Image image = images.get(position);
        ImageViewHolder viewHolder = (ImageViewHolder) holder;
        Glide.with(context)
                .load(image.getThumbnailUrl())
                .into(viewHolder.photoGridImageView);
        viewHolder.photoGridImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateToImageDetails(image.getUrl());
            }
        });
    }

    private void setItemWidthAndHeight(RecyclerView.ViewHolder holder) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int deviceWidth = displayMetrics.widthPixels / 3;
        holder.itemView.getLayoutParams().width = deviceWidth;
        holder.itemView.getLayoutParams().height = deviceWidth;
    }

    private void NavigateToImageDetails(String url) {
        SingleImageFragment singleImageFragment = new SingleImageFragment();
        singleImageFragment.setImageUrl(url);
        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrameLayout, singleImageFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public int getItemCount() {
        return images.size();
    }
}
