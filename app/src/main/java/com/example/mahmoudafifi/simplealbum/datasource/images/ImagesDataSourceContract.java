package com.example.mahmoudafifi.simplealbum.datasource.images;

import com.example.mahmoudafifi.simplealbum.model.Image;

import java.util.List;

import io.reactivex.Observable;

public interface ImagesDataSourceContract {
    Observable<List<Image>> getAllImages();

    Observable<List<Image>> getImagesByAlbumId(String albumId);
}
