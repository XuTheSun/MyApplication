package com.example.athis.myapplication.RecyclerInteract;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.R;
import com.example.athis.myapplication.dataBean.SelectBean;
import com.example.athis.myapplication.utils.AssetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DoubleRecActivity extends BaseActivity {

    @BindView(R.id.recylcer_left)
    RecyclerView recylcerLeft;
    @BindView(R.id.recycler_right)
    RecyclerView recyclerRight;

    List<SelectBean> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initRecylcer();
    }

    public void initRecylcer(){
        recylcerLeft.setLayoutManager(new LinearLayoutManager(this));
        recyclerRight.setLayoutManager(new GridLayoutManager(this,4));
    }

    public void initData(){
        Gson gson= new Gson();
        Type listType = new TypeToken<List<SelectBean>>(){}.getType();
        dataList = gson.fromJson(AssetUtil.getFileFromAsset(this),listType);
    }


    @Override
    public int getResLayout() {
        return R.layout.activity_double_rec;
    }
}
