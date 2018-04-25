package com.example.athis.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.athis.myapplication.R;
import com.example.athis.myapplication.dataBean.message;

/**
 * Created by This on 2018/4/24.
 */

public class MessageAdapter extends BaseAdapter<message> {
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false)
        , this);
    }
}
