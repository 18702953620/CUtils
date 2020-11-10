package com.h.cheng.filepicker.config;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * @author ch
 * @date 2020/10/10-16:41
 * @desc
 */
public interface ImageLoader extends Serializable {


    /**
     * 缩略图加载方案
     *
     * @param imageView imageView
     * @param imagePath imagePath
     */
    void loadImage(ImageView imageView, String imagePath);

    /**
     * 大图加载方案
     *
     * @param imageView imageView
     * @param imagePath imagePath
     */
    void loadPreImage(ImageView imageView, String imagePath);
}
