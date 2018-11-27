package com.example.mahmoudafifi.simplealbum.viewmodel.albumlist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;

import com.example.mahmoudafifi.simplealbum.model.Album;
import com.example.mahmoudafifi.simplealbum.usecase.allimages.GetAllImagesUseCase;
import com.example.mahmoudafifi.simplealbum.usecase.allimages.GetAllImagesUseCaseContract;
import com.example.mahmoudafifi.simplealbum.viewmodel.BaseViewModel;

import java.util.List;

public class AlbumListViewModel extends BaseViewModel implements AlbumListViewModeContract {

    private MutableLiveData<List<Album>> imageListMutableLiveData;
    private GetAllImagesUseCaseContract getAllImagesUseCase;

    public AlbumListViewModel() {
        imageListMutableLiveData = new MutableLiveData<>();
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


