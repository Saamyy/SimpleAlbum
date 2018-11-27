package com.example.mahmoudafifi.simplealbum.viewmodel.imageslist;

import android.arch.lifecycle.MutableLiveData;

import com.example.mahmoudafifi.simplealbum.model.Album;
import com.example.mahmoudafifi.simplealbum.model.Image;
import com.example.mahmoudafifi.simplealbum.viewmodel.BaseViewModelContract;

import java.util.List;

public interface ImageListViewModelContract extends BaseViewModelContract {

    void getImagesList();

    MutableLiveData<List<Image>> getUpdatedImageList();
}
