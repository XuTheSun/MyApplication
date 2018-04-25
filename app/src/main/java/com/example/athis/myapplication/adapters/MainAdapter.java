package com.example.athis.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.athis.myapplication.R;
import com.example.athis.myapplication.dataBean.cards;
import com.example.athis.myapplication.utils.LogUtils;

/**
 * Created by This on 2018/4/23.
 */

public class MainAdapter extends BaseAdapter<cards> {
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.d("onCreateViewHolder");
        return new MainViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false), this);
    }
}
