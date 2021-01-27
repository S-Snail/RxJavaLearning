package com.zixiu.databinding.mvvm_demo.viewmodel;

import com.zixiu.databinding.mvvm_demo.model.MvvmEntity;
import com.zixiu.databinding.mvvm_demo.model.MvvmModelImpl;
import com.zixiu.databinding.mvvm_demo.view.IViewCallback;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Author: Snail
 * Time:  2021/1/27 4:51 PM
 * FileName:  MvvmViewModel
 * 简介：
 */
public class MvvmViewModel extends ViewModel {

    private final MvvmModelImpl mvvmModel;
    private MutableLiveData<MvvmEntity> liveData;

    private MutableLiveData<List<MvvmEntity>> listLiveData;

    public MvvmViewModel() {
        mvvmModel = new MvvmModelImpl();
    }

    public MutableLiveData<MvvmEntity> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }

    public MutableLiveData<List<MvvmEntity>> getListLiveData() {
        if (listLiveData == null) {
            listLiveData = new MutableLiveData<>();
        }
        return listLiveData;
    }

    public void getDataFromNet() {
        mvvmModel.requestData(new IViewCallback<MvvmEntity>() {
            @Override
            public void onSuccess(MvvmEntity mvvmEntity) {
                liveData.setValue(mvvmEntity);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    public void getListDataFromNet() {
        mvvmModel.requestDataList(new IViewCallback<List<MvvmEntity>>() {
            @Override
            public void onSuccess(List<MvvmEntity> mvvmEntities) {
                listLiveData.setValue(mvvmEntities);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
}
