package com.example.mahmoudafifi.simplealbum.datasource.allimages;

import com.example.mahmoudafifi.simplealbum.model.Image;

import java.util.List;

import io.reactivex.Observable;

public interface AllImagesDataSourceContract {
    Observable<List<Image>> getAllImages();
}
