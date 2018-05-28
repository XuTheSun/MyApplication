package com.example.athis.myapplication.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by This on 2018/4/23.
 */

public abstract class BaseViewHolder<E> extends RecyclerView.ViewHolder {

    protected View itemView;
    protected BaseAdapter<E> adapter;
    myOnClickListener myOnClickListener;
    myOnLongClickListener myOnLongClickListener;

    public BaseViewHolder(View itemView, @Nullable BaseAdapter<E> adapter) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
        if(adapter != null){
            this.adapter = adapter;
            myOnClickListener = new myOnClickListener();
            myOnLongClickListener = new myOnLongClickListener();
            itemView.setOnClickListener(myOnClickListener);
            itemView.setOnLongClickListener(myOnLongClickListener);
        }
    }

    public void refresh(E item, int index) {
        if(myOnClickListener != null){
            myOnClickListener.index = index;
            myOnClickListener.item = item;
        }
        if(myOnLongClickListener != null){
            myOnLongClickListener.index = index;
            myOnLongClickListener.item = item;
        }
        refreshView(item, index);
    }

    public abstract void refreshView(E item, int index);

    public class myOnClickListener implements View.OnClickListener {
        E item;
        int index;
        @Override
        public void onClick(View v) {
            adapter.onItemClick(BaseViewHolder.this, item, index);
        }
    }

    public class myOnLongClickListener implements View.OnLongClickListener {
        E item;
        int index;
        @Override
        public boolean onLongClick(View v) {
            adapter.onItemLongClick(BaseViewHolder.this, item, index);
            return true;
        }
    }
}
