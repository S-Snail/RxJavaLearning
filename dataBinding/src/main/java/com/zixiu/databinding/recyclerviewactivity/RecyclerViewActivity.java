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

        list.add(new FruitItem("水果_4","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg"));
        list.add(new FruitItem("水果_5","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg"));
        list.add(new FruitItem("水果_6","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg"));
    }
}