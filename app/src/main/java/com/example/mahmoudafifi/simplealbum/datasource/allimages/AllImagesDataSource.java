package com.example.mahmoudafifi.simplealbum.datasource.allimages;

import com.example.mahmoudafifi.simplealbum.datasource.BaseDataSource;
import com.example.mahmoudafifi.simplealbum.datasource.ErrorParser;
import com.example.mahmoudafifi.simplealbum.model.ErrorModel;
import com.example.mahmoudafifi.simplealbum.model.Image;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllImagesDataSource extends BaseDataSource implements AllImagesDataSourceContract {
    private Call<List<Image>> call = client.getAllImages();

    @Override
    public Observable<List<Image>> getAllImages() {
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
