package com.zixiu.databinding.recyclerviewactivity;

import com.zixiu.databinding.BR;
import com.zixiu.databinding.R;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Author: Snail
 * Time:  2021/1/26 1:53 PM
 * FileName:  FruitItem
 * 简介：
 */
public class FruitItem extends BaseObservable implements IBaseBindingAdatperItem {

    private int picId;
    private String describe;
    private String imageUrl;

    public FruitItem(int picId, String describe) {
        this.picId = picId;
        this.describe = describe;
    }

    public FruitItem(String describe, String imageUrl) {
        this.describe = describe;
        this.imageUrl = imageUrl;
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @Bindable
    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
        notifyPropertyChanged(BR.picId);
    }

    @Bindable
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
        notifyPropertyChanged(BR.describe);
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_fruit;
    }
}
