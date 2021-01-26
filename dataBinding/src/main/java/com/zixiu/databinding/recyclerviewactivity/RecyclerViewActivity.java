package com.zixiu.databinding.recyclerviewactivity;

import android.app.Activity;
import android.os.Bundle;

import com.zixiu.databinding.R;
import com.zixiu.databinding.databinding.ActivityRecyclerViewBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;

public class RecyclerViewActivity extends Activity {

    private List<IBaseBindingAdatperItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecyclerViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view);

        initData();

        MultiAdapter multiAdapter = new MultiAdapter(this, list);
        binding.recyclerView.setAdapter(multiAdapter);
    }

    private void initData() {
        list.add(new FruitItem(R.mipmap.mvvm_img, "水果_1"));
        list.add(new FruitItem(R.mipmap.mvvm_img, "水果_2"));
        list.add(new FruitItem(R.mipmap.ic_launcher, "水果_3"));
        list.add(new TextItem("Title_01"));
        list.add(new TextItem("Title_02"));
        list.add(new TextItem("Title_03"));
    }
}