package com.example.mahmoudafifi.simplealbum.view.singleimage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mahmoudafifi.simplealbum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleImageFragment extends Fragment {

    private String url;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_image, container, false);
        imageView=view.findViewById(R.id.selectedImage);
        Glide.with(getContext())
                .load(url)
                .into(imageView);
        return view;
    }

    public void setImageUrl(String url) {
        this.url=url;
    }
}
