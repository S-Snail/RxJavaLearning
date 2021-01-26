package com.zixiu.databinding.recyclerviewactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author: Snail
 * Time:  2021/1/26 3:20 PM
 * FileName:  BaseBindingRecyclerAdapter
 * 简介：Adapter基类
 */
public abstract class BaseBindingRecyclerAdapter<T> extends RecyclerView.Adapter {

    public List<T> mList;
    public LayoutInflater mInflater;

    public BaseBindingRecyclerAdapter(Context context,List<T> mList) {
        this.mList = mList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateMyViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBindMyViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public abstract RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup viewGroup,int viewType);

    public abstract void onBindMyViewHolder(RecyclerView.ViewHolder holder,int position);

}
