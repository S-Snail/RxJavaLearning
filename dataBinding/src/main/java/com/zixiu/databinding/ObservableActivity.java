package com.zixiu.databinding;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.zixiu.databinding.databinding.ActivityObservableBinding;
import com.zixiu.databinding.entity.UserEntity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableMap;

public class ObservableActivity extends AppCompatActivity implements View.OnClickListener {

    private final ObservableArrayList observableArrayList = new ObservableArrayList();
    private final ObservableMap<String,Object> observableMap = new ObservableArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityObservableBinding observableBinding = DataBindingUtil.setContentView(this, R.layout.activity_observable);
        observableBinding.setOnClick(this);
        observableArrayList.add("测试ObservableList");
        observableMap.put("key0","测试ObservableMap");

        observableBinding.setList(observableArrayList);
        observableBinding.setMap(observableMap);

        UserEntity userEntity = new UserEntity(28, "小李");
        observableBinding.setUserEntity(userEntity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                observableBinding.userName.setText("小李同学");
            }
        },3000);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_observableList) {
            observableArrayList.add(0,"测试ObservableList成功");
        } else if (id == R.id.btn_observableMap) {
            observableMap.put("key0","测试ObservableMap成功");
        }
    }
}