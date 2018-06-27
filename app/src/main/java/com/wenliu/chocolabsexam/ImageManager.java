package com.wenliu.chocolabsexam;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageManager {
    private Context mContext;

    public ImageManager(Context context) {
        this.mContext = context;
    }

    public void loadImageUrl(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .into(imageView);
    }

}
