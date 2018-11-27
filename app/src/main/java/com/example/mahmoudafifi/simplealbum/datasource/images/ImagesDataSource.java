package com.example.mahmoudafifi.simplealbum.datasource.images;

import com.example.mahmoudafifi.simplealbum.datasource.BaseDataSource;
import com.example.mahmoudafifi.simplealbum.datasource.ErrorParser;
import com.example.mahmoudafifi.simplealbum.model.ErrorModel;
import com.example.mahmoudafifi.simplealbum.model.Image;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesDataSource extends BaseDataSource implements ImagesDataSourceContract {
    private Call<List<Image>> call;


    @Override
    public Observable<List<Image>> getAllImages() {
        call = client.getAllImages();
        return executeNetworkCall();
    }

    @Override
    public Observable<List<Image>> getImagesByAlbumId(String albumId) {
        call = client.getImagesByAlbumId(albumId);
        return executeNetworkCall();
    }

    private Observable<List<Image>> executeNetworkCall() {
        return Observable.create(observer -> {
            call.clone().enqueue(new Callback<List<Image>>() {
                @Override
                public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                    if (response.isSuccessful()) {
                        observer.onNext(response.body());
                    } else {
                        ErrorModel error = ErrorParser.parseError(response);
                        observer.onError(error);
                    }
                }
                @Override
                public void onFailure(Call<List<Image>> call, Throwable t) {
                    ErrorModel error = new ErrorModel();
                    error.setDescription(t.getMessage());
                    observer.onError(error);
                }
            });
        });
    }


}
