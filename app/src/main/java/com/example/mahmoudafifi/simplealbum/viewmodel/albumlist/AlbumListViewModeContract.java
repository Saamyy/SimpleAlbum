package com.example.mahmoudafifi.simplealbum.viewmodel.albumlist;

import android.arch.lifecycle.MutableLiveData;

import com.example.mahmoudafifi.simplealbum.model.Album;
import com.example.mahmoudafifi.simplealbum.viewmodel.BaseViewModelContract;

import java.util.List;

public interface AlbumListViewModeContract extends BaseViewModelContract {
    void getGroupedAlbums();

    MutableLiveData<List<Album>> getUpdatedGroupedAlbums();

}
