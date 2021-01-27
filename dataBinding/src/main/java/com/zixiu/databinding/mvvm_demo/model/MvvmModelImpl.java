package com.zixiu.databinding.mvvm_demo.model;


import android.os.Handler;

import com.zixiu.databinding.mvvm_demo.view.IViewCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Snail
 * Time:  2021/1/27 4:48 PM
 * FileName:  MvvmModelImpl
 * 简介：
 */
public class MvvmModelImpl {

    public void requestData(IViewCallback<MvvmEntity> viewCallback) {
        //模拟网络请求数据
        try {
            new Handler().postDelayed(() -> {
                MvvmEntity entity = new MvvmEntity("小李", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg");
                viewCallback.onSuccess(entity);
            }, 3000);
        } catch (Exception e) {
            e.printStackTrace();
            viewCallback.onFailed(e.getMessage());
        }
    }

    public void requestDataList(IViewCallback<List<MvvmEntity>> viewCallback) {
        try {
            new Handler().postDelayed(() -> {
                List<MvvmEntity> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new MvvmEntity("小王 " + i + " 号", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg"));
                }
                viewCallback.onSuccess(list);
            }, 3000);
        } catch (Exception e) {
            e.printStackTrace();
            viewCallback.onFailed(e.getMessage());
        }
    }

}
