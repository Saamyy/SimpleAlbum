package com.example.mahmoudafifi.simplealbum.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.mahmoudafifi.simplealbum.model.ErrorModel;

public class BaseViewModel extends ViewModel implements BaseViewModelContract {
    protected MutableLiveData<ErrorModel> errorModel = new MutableLiveData<>();
    protected Context context;

    @Override
    public MutableLiveData<ErrorModel> getErrorModel() {
        return errorModel;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
