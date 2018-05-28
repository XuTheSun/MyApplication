package com.example.athis.myapplication.RecyclerInteract;

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

    @Override
    public LeftHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeftHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_rec_left, parent, false), this);
    }

    public static class LeftHolder extends BaseViewHolder<SelectBean> {

        @BindView(R.id.name_left)
        TextView nameLeft;

        public LeftHolder(View itemView, @Nullable LeftAdapter adapter) {
            super(itemView, adapter);
        }

        @Override
        public void refreshView(SelectBean item, int index) {
            nameLeft.setText(item.getLname());
        }
    }
}
