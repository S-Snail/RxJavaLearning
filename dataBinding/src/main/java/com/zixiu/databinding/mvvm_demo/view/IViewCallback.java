package com.zixiu.databinding.mvvm_demo.view;

/**
 * Author: Snail
 * Time:  2021/1/27 5:13 PM
 * FileName:  IViewCallback
 * 简介：
 */
public interface IViewCallback<T> {

    void onSuccess(T t);

    void onFailed(String msg);

}
