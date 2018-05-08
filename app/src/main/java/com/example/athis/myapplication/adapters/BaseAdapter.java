package com.example.athis.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.athis.myapplication.utils.LogUtils;

import java.util.List;

/**
 * Created by This on 2018/4/23.
 */

public abstract class BaseAdapter<E> extends RecyclerView.Adapter<BaseViewHolder<E>> {

    List<E> listData;
    onClickListener<E> onClickListener;
    onLongClickListener<E> onLongClickListener;

    @Override
    public void onBindViewHolder(BaseViewHolder<E> holder, int position) {
        LogUtils.d("onBindViewHolder :" + position);
        holder.refresh(listData.get(position), position);
    }

    public void setOnClickListener(BaseAdapter.onClickListener<E> onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(BaseAdapter.onLongClickListener<E> onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public void setListData(List<E> listData){
        this.listData = listData;
    }

    public void addItem(E item){
        listData.add(item);
        notifyItemInserted(listData.indexOf(item));
    }

    public void removeItem(E item, int index){
        listData.remove(index);
        notifyItemRemoved(index);
    }

    public void onItemClick(BaseViewHolder<E> holder, E item, int index){
        if(onClickListener != null){
            onClickListener.onClick(holder, item, index);
        }
    }

    public void onItemLongClick(BaseViewHolder<E> holder, E item, int index){
        if(onLongClickListener != null){
            onLongClickListener.onLongClick(holder, item, index);
        }
    }

    @Override
    public int getItemCount() {
        return listData == null || listData.size() < 1 ?  0 : listData.size();
    }

    public interface onClickListener<E>{
        void onClick(BaseViewHolder<E> holder, E item, int index);
    }
    public interface onLongClickListener<E>{
        void onLongClick(BaseViewHolder<E> holder, E item, int index);
    }
}
