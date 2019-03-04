package com.matias.journeytodependencyinjection.common;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

public class ImageLoader {

    private final Activity activity;

    private final RequestOptions mDefaultRequestOptions;

    @Inject
    public ImageLoader(Activity activity) {
        this.activity = activity;

        mDefaultRequestOptions = new RequestOptions()
                .centerCrop();
    }

    public void loadImage(String uri, ImageView target) {
        Glide.with(activity).load(uri).apply(mDefaultRequestOptions).into(target);
    }
}
