package com.zixiu.databinding.mvvm_demo.model;

import com.zixiu.databinding.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Author: Snail
 * Time:  2021/1/27 4:40 PM
 * FileName:  Entity
 * 简介：
 */
public class MvvmEntity extends BaseObservable {

    private String name;

    private String avatar;

    public MvvmEntity(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }
}
