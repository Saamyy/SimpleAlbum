package com.example.mahmoudafifi.simplealbum.view.albumList;



import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mahmoudafifi.simplealbum.R;
import com.example.mahmoudafifi.simplealbum.model.Album;
import com.example.mahmoudafifi.simplealbum.model.ErrorModel;
import com.example.mahmoudafifi.simplealbum.viewmodel.albumlist.AlbumListViewModeContract;
import com.example.mahmoudafifi.simplealbum.viewmodel.albumlist.AlbumListViewModel;
import com.example.mahmoudafifi.simplealbum.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumListFragment extends Fragment {


    private RecyclerView albumsRecyclerView;
    private ProgressBar progressBar;
    private AlbumListAdapter albumListAdapter;
    private AlbumListViewModeContract albumListViewModeContract;

    public AlbumListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_list, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        albumsRecyclerView = view.findViewById(R.id.albumRecyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        albumListViewModeContract = ViewModelProviders.of(this).get(AlbumListViewModel.class);
        albumListViewModeContract.setContext(getContext());
        getLifecycle().addObserver(albumListViewModeContract);
        albumListViewModeContract.getUpdatedGroupedAlbums()
                .observe(this, this::showAlbums);
        albumListViewModeContract.getErrorModel()
                .observe(this, this::showError);
    }


    private void showAlbums(List<Album> albums) {
        albumsRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        albumListAdapter = new AlbumListAdapter(albums, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        albumsRecyclerView.setLayoutManager(mLayoutManager);
        albumsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        albumsRecyclerView.setAdapter(albumListAdapter);
        albumListAdapter.notifyDataSetChanged();
    }

    private void handleViewVisiblty(){

    }

    private void showError(ErrorModel errorModel) {
        albumsRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), errorModel.getDescription(), Toast.LENGTH_LONG).show();
    }


}
