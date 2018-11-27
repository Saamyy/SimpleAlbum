package com.example.mahmoudafifi.simplealbum.network;


import com.example.mahmoudafifi.simplealbum.model.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRetrofit {

    @GET("/photos")
    Call<List<Image>> getAllImages();

    @GET("/photos")
    Call<List<Image>> getImagesByAlbumId(@Query("albumId") String albumId);
}
