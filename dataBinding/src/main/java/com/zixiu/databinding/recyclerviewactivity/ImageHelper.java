package com.zixiu.databinding.recyclerviewactivity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zixiu.databinding.R;

import androidx.databinding.BindingAdapter;

/**
 * Author: Snail
 * Time:  2021/1/26 4:51 PM
 * FileName:  ImageHelper
 * 简介：
 */
public class ImageHelper {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

}
