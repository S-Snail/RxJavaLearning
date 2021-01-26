package com.zixiu.databinding;

import android.view.View;
import android.widget.Toast;

/**
 * Author: Snail
 * Time:  2021/1/26 10:36 AM
 * FileName:  ClickHandler
 * 简介：
 */
public class ClickHandler {

    public void onClickHandler(View view){
        Toast.makeText(view.getContext(), "onClickHandler", Toast.LENGTH_SHORT).show();
    }

}
