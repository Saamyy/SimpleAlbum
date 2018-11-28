package com.example.mahmoudafifi.simplealbum.view.singleimage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahmoudafifi.simplealbum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleImageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_image, container, false);
    }

    public void setImageUrl(String url) {

    }
}
