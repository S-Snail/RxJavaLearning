package com.zixiu.databinding;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zixiu.databinding.databinding.ActivityDataBindingBinding;
import com.zixiu.databinding.entity.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.databinding.DataBindingUtil;

public class DataBindingActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        UserEntity userEntity = new UserEntity(28, "小李");
        binding.setUserEntity(userEntity);

        List<String> list = new ArrayList<>();
        list.add("list -> 0");
        list.add("list -> 1");
        list.add("list -> 2");
        binding.setList(list);
        Map<String, Object> map = new HashMap<>();
        map.put("key0", "key -> 0");
        map.put("key1", "key -> 1");
        map.put("key2", "key -> 2");
        binding.setMap(map);
        String[] array = new String[3];
        array[0] = "array -> 0";
        array[1] = "array -> 1";
        array[2] = "array -> 2";
        binding.setArray(array);

        binding.setUserEntity1(userEntity);

        com.zixiu.databinding.annotherEntity.UserEntity userEntiry2 = new com.zixiu.databinding.annotherEntity.UserEntity(2, 8, "小小李");
        binding.setUserEntity2(userEntiry2);

        binding.setClickListener(this);

        binding.setOnClickHandler(new ClickHandler());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_click_1) {
            Toast.makeText(this, "点击事件1", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btn_click_2) {
            Toast.makeText(this, "点击事件2", Toast.LENGTH_SHORT).show();
        }
    }
}