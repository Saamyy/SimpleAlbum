package com.example.mahmoudafifi.simplealbum.usecase.imagesbyalbumid;

import android.arch.lifecycle.MutableLiveData;

import com.example.mahmoudafifi.simplealbum.datasource.images.ImagesDataSource;
import com.example.mahmoudafifi.simplealbum.model.ErrorModel;
import com.example.mahmoudafifi.simplealbum.model.Image;
import com.example.mahmoudafifi.simplealbum.usecase.BaseUseCase;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetAllImagedByIDUseCase extends BaseUseCase implements GetAllImagedByIDUseCaseContract {

    private final String albumId;
    private MutableLiveData<List<Image>> imageListMutableLiveData;
    private MutableLiveData<ErrorModel> errorMessage;
    private CompositeDisposable bag;

    public GetAllImagedByIDUseCase(MutableLiveData<List<Image>> imageListMutableLiveData,
                                   MutableLiveData<ErrorModel> errorMessage, String albumId) {
        allImagesDataSourceContract = new ImagesDataSource();
        this.imageListMutableLiveData = imageListMutableLiveData;
        this.errorMessage = errorMessage;
        this.albumId = albumId;
        bag = new CompositeDisposable();
    }


    @Override
    public void getAllImagesByID() {
        Disposable disposable = allImagesDataSourceContract.getImagesByAlbumId(albumId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(images -> {
                    imageListMutableLiveData.setValue(images);
                }, errorMode -> {
                    errorMessage.setValue((ErrorModel) errorMode);
                });
        bag.add(disposable);
    }

    @Override
    public void DisposeObservable() {
        bag.clear();
    }
}
