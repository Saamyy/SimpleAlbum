package com.example.mahmoudafifi.simplealbum.datasource;


import com.example.mahmoudafifi.simplealbum.network.ApiRetrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseDataSource implements BaseDataSourceContract {
    protected String API_BASE_URL = "https://jsonplaceholder.typicode.com";

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public Retrofit retrofit = getRetrofitCall();
    protected ApiRetrofit client = retrofit.create(ApiRetrofit.class);

    @Override
    public Retrofit getRetrofitCall() {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(httpClient.build())
                        .build();
        return retrofit;
    }
}
