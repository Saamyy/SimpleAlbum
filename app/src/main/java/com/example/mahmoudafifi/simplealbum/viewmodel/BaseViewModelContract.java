package com.example.mahmoudafifi.simplealbum.viewmodel;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.mahmoudafifi.simplealbum.model.ErrorModel;

public interface BaseViewModelContract extends LifecycleObserver {
    MutableLiveData<ErrorModel> getErrorModel();

    void setContext(Context context);

}
