package com.zixiu.databinding.recyclerviewactivity;

import com.zixiu.databinding.BR;
import com.zixiu.databinding.R;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Author: Snail
 * Time:  2021/1/26 2:58 PM
 * FileName:  TextItem
 * 简介：
 */
public class TextItem extends BaseObservable implements IBaseBindingAdatperItem {

    private String title;

    public TextItem(String title) {
        this.title = title;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_text;
    }
}
