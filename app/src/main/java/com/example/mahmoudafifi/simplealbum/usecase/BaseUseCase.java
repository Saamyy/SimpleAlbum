package com.example.mahmoudafifi.simplealbum.usecase;

import com.example.mahmoudafifi.simplealbum.datasource.images.ImagesDataSourceContract;

public abstract class BaseUseCase {
    protected ImagesDataSourceContract allImagesDataSourceContract;

    public abstract void DisposeObservable();

}
