package com.example.mahmoudafifi.simplealbum.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mahmoudafifi.simplealbum.R;
import com.example.mahmoudafifi.simplealbum.view.albumList.AlbumListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlbumListFragment AlbumListFragment = new AlbumListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrameLayout, AlbumListFragment)
                .commit();
    }
}
