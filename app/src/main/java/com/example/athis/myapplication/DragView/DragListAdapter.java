package com.example.athis.myapplication.DragView;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.athis.myapplication.R;
import com.example.athis.myapplication.adapters.BaseAdapter;
import com.example.athis.myapplication.adapters.BaseViewHolder;
import com.example.athis.myapplication.dataBean.cards;

import butterknife.BindView;

public class DragListAdapter extends BaseAdapter<cards> {

    @Override
    public DragListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DragListHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false), this);
    }

    public static class DragListHolder extends BaseViewHolder<cards> {

        @BindView(R.id.tv)
        TextView tv;

        public DragListHolder(View itemView, @Nullable BaseAdapter adapter) {
            super(itemView, adapter);
        }

        @Override
        public void refreshView(cards item, int index) {
            tv.setText(item.name);
        }
    }
}
