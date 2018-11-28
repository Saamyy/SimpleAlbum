package com.example.mahmoudafifi.simplealbum.viewmodel.imageslist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;

import com.example.mahmoudafifi.simplealbum.model.Image;
import com.example.mahmoudafifi.simplealbum.usecase.imagesbyalbumid.GetAllImagedByIDUseCase;
import com.example.mahmoudafifi.simplealbum.usecase.imagesbyalbumid.GetAllImagedByIDUseCaseContract;
import com.example.mahmoudafifi.simplealbum.viewmodel.BaseViewModel;

import java.util.List;

public class ImageListViewModel extends BaseViewModel implements ImageListViewModelContract {
    private MutableLiveData<List<Image>> imagesList;
    private GetAllImagedByIDUseCaseContract getAllImagedByIDUseCase;
    private String albumId;

    public ImageListViewModel() {
        imagesList=new MutableLiveData<>();
    }
    @Override
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @Override
    public void getImagesList() {
        getAllImagedByIDUseCase=new GetAllImagedByIDUseCase(imagesList,errorModel,albumId);
        getAllImagedByIDUseCase.getAllImagesByID();
    }

    @Override
    public MutableLiveData<List<Image>> getUpdatedImageList() {
        return imagesList;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        getAllImagedByIDUseCase.DisposeObservable();
    }
}
