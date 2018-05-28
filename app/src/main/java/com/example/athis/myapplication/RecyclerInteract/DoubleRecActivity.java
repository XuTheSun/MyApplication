package com.example.athis.myapplication.RecyclerInteract;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.R;
import com.example.athis.myapplication.adapters.BaseAdapter;
import com.example.athis.myapplication.adapters.BaseViewHolder;
import com.example.athis.myapplication.dataBean.SelectBean;
import com.example.athis.myapplication.utils.AssetUtil;
import com.example.athis.myapplication.utils.DoubleRecUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DoubleRecActivity extends BaseActivity {

    @BindView(R.id.recylcer_left)
    RecyclerView recyclerLeft;
    @BindView(R.id.recycler_right)
    RecyclerView recyclerRight;

    LeftAdapter leftAdapter;
    RightAdapter rightAdapter;

    List<SelectBean> leftDataList = new ArrayList<>();
    List<SelectBean.HobbiesBean> rightDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initRecycler();
    }

    public void initRecycler(){
        recyclerLeft.setLayoutManager(new LinearLayoutManager(this));
        leftAdapter = new LeftAdapter();
        rightAdapter = new RightAdapter();
        recyclerLeft.setAdapter(leftAdapter);
        leftAdapter.setListData(leftDataList);
        leftAdapter.notifyDataSetChanged();
        GridLayoutManager manager = new GridLayoutManager(this,3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(DoubleRecUtils.isTitle(rightDataList.get(position)))
                    return 3;
                return 1;
            }
        });
        recyclerRight.setLayoutManager(manager);
        rightAdapter.setListData(rightDataList);
        rightAdapter.notifyDataSetChanged();
        recyclerRight.setAdapter(rightAdapter);
    }

    public void initListener(){
        leftAdapter.setOnClickListener(new BaseAdapter.onClickListener<SelectBean>() {
            @Override
            public void onClick(BaseViewHolder<SelectBean> holder, SelectBean item, int index) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerLeft.getLayoutManager();
            }
        });
    }

    public void initData(){
        Gson gson= new Gson();
        leftDataList = gson.fromJson(AssetUtil.getFileFromAsset(this),new TypeToken<List<SelectBean>>(){}.getType());
        List<SelectBean.HobbiesBean> temp = gson.fromJson(AssetUtil.getFileFromAsset(this),new TypeToken<List<SelectBean.HobbiesBean>>(){}.getType());
        for(int i = 0; i < temp.size(); i++){
            rightDataList.add(temp.get(i));
            rightDataList.addAll(leftDataList.get(i).getHobbies());
        }
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_double_rec;
    }
}
