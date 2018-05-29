package com.example.athis.myapplication.RecyclerInteract;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.athis.myapplication.R;
import com.example.athis.myapplication.adapters.BaseAdapter;
import com.example.athis.myapplication.adapters.BaseViewHolder;
import com.example.athis.myapplication.dataBean.SelectBean;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LeftAdapter extends BaseAdapter<SelectBean> {

    private boolean[] isSelected = new boolean[50];
    int count = 0;

    @Override
    public LeftHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("onCreateViewHolder", count++ +"");
        return new LeftHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_rec_left, parent, false), this);
    }

    public void setIsSelected(int position){
        for(int i = 0; i < listData.size(); i++){
            isSelected[i] = false;
        }
        isSelected[position] = true;
    }

    public boolean isSelected(int position){
        return isSelected[position];
    }

    public static class LeftHolder extends BaseViewHolder<SelectBean> {

        @BindView(R.id.name_left)
        TextView nameLeft;

        public LeftHolder(View itemView, @Nullable LeftAdapter adapter) {
            super(itemView, adapter);
        }

        @Override
        public void refreshView(SelectBean item, int index) {
            if(((LeftAdapter)adapter).isSelected(index)){
                itemView.setBackgroundColor(Color.GRAY);
            }else{
                itemView.setBackgroundColor(Color.WHITE);
            }
            nameLeft.setText(item.getLname());
        }
    }
}
