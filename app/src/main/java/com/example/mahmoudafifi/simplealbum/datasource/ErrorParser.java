package com.example.mahmoudafifi.simplealbum.datasource;


import com.example.mahmoudafifi.simplealbum.model.ErrorModel;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorParser {
    public static ErrorModel parseError(Response<?> response) {
        BaseDataSourceContract baseDataSource = new BaseDataSource();
        Converter<ResponseBody, ErrorModel> converter =
                baseDataSource.getRetrofitCall()
                        .responseBodyConverter(ErrorModel.class, new Annotation[0]);
        ErrorModel error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorModel();
        }

        return error;
    }
}
