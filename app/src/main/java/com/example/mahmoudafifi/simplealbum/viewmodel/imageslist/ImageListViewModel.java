package com.example.mahmoudafifi.simplealbum.viewmodel.imageslist;

import android.arch.lifecycle.MutableLiveData;

import com.example.mahmoudafifi.simplealbum.model.Image;
import com.example.mahmoudafifi.simplealbum.usecase.imagesbyalbumid.GetAllImagedByIDUseCase;
import com.example.mahmoudafifi.simplealbum.usecase.imagesbyalbumid.GetAllImagedByIDUseCaseContract;
import com.example.mahmoudafifi.simplealbum.viewmodel.BaseViewModel;

import java.util.List;

public class ImageListViewModel extends BaseViewModel implements ImageListViewModelContract {
    private MutableLiveData<List<Image>> imagesList;
    private GetAllImagedByIDUseCaseContract getAllImagedByIDUseCase;

    public ImageListViewModel(String albumId) {
        imagesList=new MutableLiveData<>();
        getAllImagedByIDUseCase=new GetAllImagedByIDUseCase(imagesList,errorModel,albumId);
    }

    @Override
    public void getImagesList() {
        getAllImagedByIDUseCase.getAllImagesByID();
    }

    @Override
    public MutableLiveData<List<Image>> getUpdatedImageList() {
        return imagesList;
    }
}
