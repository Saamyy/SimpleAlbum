package com.example.mahmoudafifi.simplealbum.usecase;

import android.arch.lifecycle.MutableLiveData;

import com.example.mahmoudafifi.simplealbum.datasource.images.ImagesDataSource;
import com.example.mahmoudafifi.simplealbum.datasource.images.ImagesDataSourceContract;
import com.example.mahmoudafifi.simplealbum.model.Album;
import com.example.mahmoudafifi.simplealbum.model.ErrorModel;
import com.example.mahmoudafifi.simplealbum.model.Image;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class GetAllImagesUseCase implements GetAllImagesUseCaseContract {
    private ImagesDataSourceContract allImagesDataSourceContract;
    private MutableLiveData<List<Album>> imageListMutableLiveData;
    private MutableLiveData<ErrorModel> errorMessage;
    private CompositeDisposable bag;

    public GetAllImagesUseCase(MutableLiveData<List<Album>> imageListMutableLiveData,
                               MutableLiveData<ErrorModel> errorMessage) {
        allImagesDataSourceContract = new ImagesDataSource();
        this.imageListMutableLiveData = imageListMutableLiveData;
        this.errorMessage = errorMessage;
        bag = new CompositeDisposable();
    }

    @Override
    public void getAllImagesFromApi() {
        Disposable disposable = allImagesDataSourceContract.getAllImages().observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .subscribe(images -> {
                    applyBusinessRules(images);
                }, errorMode -> {
                    errorMessage.postValue((ErrorModel) errorMode);
                });
        bag.add(disposable);
    }


    private void applyBusinessRules(List<Image> images) {
        imageListMutableLiveData.postValue(mapToAlbum(groupByAlbumId(images)));
    }

    private LinkedHashMap<Integer, List<Image>> groupByAlbumId(List<Image> images) {
        LinkedHashMap<Integer, List<Image>> groupedAlbumsHashMap = new LinkedHashMap();
        for (Image image : images) {
            int albumId = image.getAlbumId();
            if (!groupedAlbumsHashMap.containsKey(albumId)) {
                List<Image> list = new ArrayList();
                list.add(image);
                groupedAlbumsHashMap.put(albumId, list);
            } else {
                groupedAlbumsHashMap.get(albumId).add(image);
            }
        }
        return groupedAlbumsHashMap;
    }

    private List<Album> mapToAlbum(LinkedHashMap<Integer, List<Image>> groupedAlbumsHashMap) {
        List<Album> albumsList = new LinkedList<>();
        List<Image> currentImageList = new ArrayList<>();

        for (Integer albumId : groupedAlbumsHashMap.keySet()) {
            currentImageList = groupedAlbumsHashMap.get(albumId);
            albumsList.add(new Album(Integer.toString(albumId)
                    , currentImageList.get(0).getTitle()
                    , currentImageList.get(0).getThumbnailUrl()
                    , Integer.toString(currentImageList.size())));
        }
        return albumsList;
    }

    @Override
    public void DisposeObservable() {
        bag.clear();
    }
}
