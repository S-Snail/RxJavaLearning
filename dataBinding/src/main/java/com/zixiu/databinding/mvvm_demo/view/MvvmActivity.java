package com.zixiu.databinding.mvvm_demo.view;

import android.os.Bundle;
import android.widget.Toast;

import com.zixiu.databinding.R;
import com.zixiu.databinding.databinding.ActivityMvvmBinding;
import com.zixiu.databinding.mvvm_demo.model.MvvmEntity;
import com.zixiu.databinding.mvvm_demo.view.adapter.MvvmListAdapter;
import com.zixiu.databinding.mvvm_demo.viewmodel.MvvmViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class MvvmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        MvvmViewModel viewModel = new MvvmViewModel();
        MutableLiveData<MvvmEntity> liveData = viewModel.getLiveData();
        viewModel.getDataFromNet();
        liveData.observe(this, binding::setMvvmEntity);


        //实现列表功能
        ArrayList<MvvmEntity> mvvmEntities = new ArrayList<>();
        MvvmListAdapter mvvmListAdapter = new MvvmListAdapter(this, mvvmEntities);
        binding.rv.setAdapter(mvvmListAdapter);
        MutableLiveData<List<MvvmEntity>> listLiveData = viewModel.getListLiveData();
        viewModel.getListDataFromNet();
        listLiveData.observe(this, new Observer<List<MvvmEntity>>() {
            @Override
            public void onChanged(List<MvvmEntity> list) {
                Toast.makeText(MvvmActivity.this, "list.size = " + list.size(), Toast.LENGTH_SHORT).show();
                mvvmEntities.addAll(list);
                mvvmListAdapter.notifyDataSetChanged();
            }
        });

    }
}