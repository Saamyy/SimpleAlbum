package com.example.mahmoudafifi.simplealbum.view.imagelist;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mahmoudafifi.simplealbum.R;
import com.example.mahmoudafifi.simplealbum.model.Album;
import com.example.mahmoudafifi.simplealbum.model.ErrorModel;
import com.example.mahmoudafifi.simplealbum.model.Image;
import com.example.mahmoudafifi.simplealbum.viewmodel.albumlist.AlbumListViewModel;
import com.example.mahmoudafifi.simplealbum.viewmodel.imageslist.ImageListViewModel;
import com.example.mahmoudafifi.simplealbum.viewmodel.imageslist.ImageListViewModelContract;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesListFragment extends Fragment {
    private RecyclerView imagesRecyclerView;
    private ProgressBar progressBar;
    private ImagesListAdapter imagesListAdapter;
    private ImageListViewModelContract imageListViewModelContract;
    private String albumID;
    private final  int numberOfColumns = 3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images_list, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        imagesRecyclerView = view.findViewById(R.id.imagesRecyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        imageListViewModelContract = ViewModelProviders.of(this).get(ImageListViewModel.class);
        imageListViewModelContract.setContext(getContext());
        getImagesByAlbum();
        imageListViewModelContract.getUpdatedImageList()
                .observe(this, this::showImages);
        imageListViewModelContract.getErrorModel()
                .observe(this, this::showError);
    }

    private void getImagesByAlbum() {
        if (albumID != null) {
            imageListViewModelContract = new ImageListViewModel();
            imageListViewModelContract.setAlbumId(albumID);
            imageListViewModelContract.getImagesList();
        }
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    private void showImages(List<Image> images) {
        imagesRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        imagesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),numberOfColumns));
        imagesListAdapter = new ImagesListAdapter(images,getContext());
        imagesRecyclerView.setAdapter(imagesListAdapter);
    }

    private void showError(ErrorModel errorModel) {
        imagesRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), errorModel.getDescription(), Toast.LENGTH_LONG).show();
    }

}
