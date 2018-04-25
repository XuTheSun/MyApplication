package com.example.athis.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.athis.myapplication.R;
import com.example.athis.myapplication.dataBean.cards;
import com.example.athis.myapplication.utils.LogUtils;

/**
 * Created by This on 2018/4/23.
 */

public class MainViewHolder extends BaseViewHolder<cards> {

    public MainViewHolder(View itemView, MainAdapter adapter) {
        super(itemView, adapter);
    }

    @Override
    public void refreshView(cards item, int index) {
        LogUtils.d("refreshView :" + index);
        TextView tv = itemView.findViewById(R.id.tv);
        tv.setText(item.name);
    }
}
