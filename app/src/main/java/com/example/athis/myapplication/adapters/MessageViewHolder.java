package com.example.athis.myapplication.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.athis.myapplication.R;
import com.example.athis.myapplication.dataBean.message;

/**
 * Created by This on 2018/4/24.
 */

public class MessageViewHolder extends BaseViewHolder<message> {

    public MessageViewHolder(View itemView) {
        this(itemView, null);
    }

    public MessageViewHolder(View itemView, @Nullable BaseAdapter<message> adapter) {
        super(itemView, adapter);
    }

    @Override
    public void refreshView(message item, int index) {
        CardView cardView = itemView.findViewById(R.id.card);
        TextView tv_message = itemView.findViewById(R.id.tv);
        tv_message.setText(item.content);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20,20,20,20);
        if(item.status == message.STATUS_RECEIVE){
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            cardView.setLayoutParams(params);
        }else{
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            cardView.setLayoutParams(params);
        }
    }
}
