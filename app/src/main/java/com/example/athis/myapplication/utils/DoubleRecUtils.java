package com.example.athis.myapplication.utils;

import android.text.TextUtils;

import com.example.athis.myapplication.dataBean.SelectBean;

public class DoubleRecUtils {
    public static boolean isTitle(SelectBean.HobbiesBean data){
        if(TextUtils.isEmpty(data.getFlid()))
            return true;
        return false;
    }
}
