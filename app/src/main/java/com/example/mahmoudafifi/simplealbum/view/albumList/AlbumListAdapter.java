package com.example.mahmoudafifi.simplealbum.view.albumList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mahmoudafifi.simplealbum.R;
import com.example.mahmoudafifi.simplealbum.model.Album;

import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Album> albumList;
    private Context context;


    public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView firstCard;
        private ImageView albumImageImage;
        private TextView albumId;
        private TextView title;
        private TextView ImagesCount;


        public AlbumViewHolder(View itemView) {
            super(itemView);
            firstCard = itemView.findViewById(R.id.first_card);
            albumImageImage = itemView.findViewById(R.id.albumImageImage);
            albumId = itemView.findViewById(R.id.albumId);
            title = itemView.findViewById(R.id.title);
            ImagesCount = itemView.findViewById(R.id.ImagesCount);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public AlbumListAdapter(List<Album> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.album_item, viewGroup, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Album album = albumList.get(position);
        AlbumViewHolder viewHolder = (AlbumViewHolder) holder;
        Glide.with(context)
                .load(album.getThumbnailUrl())
                .into(viewHolder.albumImageImage);

        viewHolder.title.setText(album.getTitle());
        viewHolder.albumId.setText(String.format("%s%s%s", context.getResources().getString(R.string.album_id)," ", album.getAlbumId()));
        viewHolder.ImagesCount.setText(String.format("%s%s%s", context.getResources().getString(R.string.count)," ", album.getImagesCount()));
        viewHolder.firstCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateToSongDetails(album.getAlbumId());
            }
        });
    }

    private void NavigateToSongDetails(String albumId) {

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
