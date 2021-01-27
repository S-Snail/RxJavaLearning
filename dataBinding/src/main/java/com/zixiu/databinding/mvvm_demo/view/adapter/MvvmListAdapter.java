package com.zixiu.databinding.mvvm_demo.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zixiu.databinding.R;
import com.zixiu.databinding.databinding.ItemMvvmListBinding;
import com.zixiu.databinding.mvvm_demo.model.MvvmEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author: Snail
 * Time:  2021/1/27 5:35 PM
 * FileName:  MvvmListAdapter
 * 简介：
 */
public class MvvmListAdapter extends RecyclerView.Adapter {

    private final LayoutInflater inflater;
    private final List<MvvmEntity> mList;
    private final Context context;

    public MvvmListAdapter(Context context, List<MvvmEntity> list) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMvvmListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_mvvm_list, parent, false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListViewHolder) {
            ((ListViewHolder) holder).binding.setMvvmEntity(mList.get(position));
            ((ListViewHolder) holder).binding.setPosition(position);
            ((ListViewHolder) holder).binding.setAdapter(this);//真坑，这里还要绑定Adapter,否则xml中调用Adapter中的方式无效
            ((ListViewHolder) holder).binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void onItemClickListener(MvvmEntity entity, int position) {
        Log.d("测试点击事件", entity.getName() + " position = " + position);
        Toast.makeText(context, entity.getName() + "\t postion = " + position, Toast.LENGTH_SHORT).show();
    }

    private static class ListViewHolder extends RecyclerView.ViewHolder {
        private ItemMvvmListBinding binding;

        public ListViewHolder(ItemMvvmListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
