package com.ch.cutils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.h.cheng.filepicker.config.ImageLoader;

/**
 * @author ch
 * @date 2020/10/10-16:46
 * @desc
 */
public class MyImgLoader implements ImageLoader {
    @Override
    public void loadImage(ImageView imageView, String imagePath) {
        Glide.with(imageView.getContext())
                .load(imagePath)
                .apply(new RequestOptions()
                        .override(200, 200))
                .into(imageView);
    }

    @Override
    public void loadPreImage(ImageView imageView, String imagePath) {
        Glide.with(imageView.getContext())
                .load(imagePath)
                .apply(new RequestOptions().centerCrop())
                .into(imageView);
    }
}
