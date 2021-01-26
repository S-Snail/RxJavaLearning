package com.zixiu.databinding.recyclerviewactivity;

import android.content.Context;
import android.view.ViewGroup;

import com.zixiu.databinding.R;
import com.zixiu.databinding.databinding.ItemFruitBinding;
import com.zixiu.databinding.databinding.ItemTextBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author: Snail
 * Time:  2021/1/26 3:29 PM
 * FileName:  MultiAdapter
 * 简介：
 */
public class MultiAdapter extends BaseBindingRecyclerAdapter<IBaseBindingAdatperItem> {

    public MultiAdapter(Context context, List<IBaseBindingAdatperItem> mList) {
        super(context, mList);
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_fruit) {
            ItemFruitBinding fruitBinding = DataBindingUtil.inflate(mInflater, R.layout.item_fruit, parent, false);
            return new FruitViewHolder(fruitBinding);
        } else if (viewType == R.layout.item_text) {
            ItemTextBinding itemtextBinding = DataBindingUtil.inflate(mInflater, R.layout.item_text, parent, false);
            return new TextViewHolder(itemtextBinding);
        }
        return null;
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FruitViewHolder) {
            FruitItem fruitEntity = (FruitItem) mList.get(position);
            ((FruitViewHolder) holder).binding.setItem(fruitEntity);
            ((FruitViewHolder) holder).binding.executePendingBindings();
        } else if (holder instanceof TextViewHolder) {
            TextItem textItem = (TextItem) mList.get(position);
            ((TextViewHolder) holder).binding.setTextItem(textItem);
            ((TextViewHolder) holder).binding.executePendingBindings();
        }
    }

    private static class FruitViewHolder extends RecyclerView.ViewHolder {

        private ItemFruitBinding binding;

        public FruitViewHolder(ItemFruitBinding fruitBinding) {
            super(fruitBinding.getRoot());
            this.binding = fruitBinding;
        }
    }

    private static class TextViewHolder extends RecyclerView.ViewHolder {

        private ItemTextBinding binding;

        public TextViewHolder(ItemTextBinding textBinding) {
            super(textBinding.getRoot());
            this.binding = textBinding;
        }
    }
}
