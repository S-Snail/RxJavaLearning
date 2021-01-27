package com.zixiu.databinding.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/**
 * Author: Snail
 * Time:  2021/1/27 10:53 AM
 * FileName:  DoubleBindEntity
 * 简介：
 */
public class DoubleBindEntity extends BaseObservable {

    private String content;

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }
}
