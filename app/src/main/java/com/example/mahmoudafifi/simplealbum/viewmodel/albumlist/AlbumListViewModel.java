package com.example.mahmoudafifi.simplealbum.viewmodel.albumlist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;

import com.example.mahmoudafifi.simplealbum.model.Album;
import com.example.mahmoudafifi.simplealbum.usecase.GetAllImagesUseCase;
import com.example.mahmoudafifi.simplealbum.usecase.GetAllImagesUseCaseContract;
import com.example.mahmoudafifi.simplealbum.viewmodel.BaseViewModel;

import java.util.List;

public class AlbumListViewModel extends BaseViewModel implements AlbumListViewModeContract {

    private MutableLiveData<List<Album>> imageListMutableLiveData = new MutableLiveData<>();
    private GetAllImagesUseCaseContract getAllImagesUseCase;

    public AlbumListViewModel() {
        getAllImagesUseCase = new GetAllImagesUseCase(imageListMutableLiveData, errorModel);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void getGroupedAlbums() {
        getAllImagesUseCase.getAllImagesFromApi();
    }

    @Override
    public MutableLiveData<List<Album>> getUpdatedGroupedAlbums() {
        return imageListMutableLiveData;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        getAllImagesUseCase.DisposeObservable();
    }

}


