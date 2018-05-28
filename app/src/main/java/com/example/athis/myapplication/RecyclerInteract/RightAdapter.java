package com.example.athis.myapplication.RecyclerInteract;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.athis.myapplication.R;
import com.example.athis.myapplication.adapters.BaseAdapter;
import com.example.athis.myapplication.adapters.BaseViewHolder;
import com.example.athis.myapplication.dataBean.SelectBean;
import com.example.athis.myapplication.utils.DoubleRecUtils;

import org.w3c.dom.Text;

import butterknife.BindView;

public class RightAdapter extends BaseAdapter<SelectBean.HobbiesBean> {

    public static final int VIEW_CONTENT = 0;
    public static final int VIEW_TITLE = 1;

    @Override
    public RightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_CONTENT:
                return new RightHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_item_rec_right_sub,parent,false)
                        ,this);
            case VIEW_TITLE:
                return new RightHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_item_rec_right_title,parent,false)
                        ,this);

            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(DoubleRecUtils.isTitle(listData.get(position)))
            return VIEW_TITLE;
        return VIEW_CONTENT;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    public static class RightHolder extends BaseViewHolder<SelectBean.HobbiesBean>{

        TextView tvContent;
        TextView tvTitle;

        public RightHolder(View itemView, @Nullable BaseAdapter<SelectBean.HobbiesBean> adapter) {
            super(itemView, adapter);
        }

        @Override
        public void refreshView(SelectBean.HobbiesBean item, int index) {
            if(DoubleRecUtils.isTitle(item)){
                tvTitle = itemView.findViewById(R.id.title);
                tvTitle.setText(item.getLname());
            }else{
                tvContent = itemView.findViewById(R.id.content);
                tvContent.setText(item.getLname());
            }
        }
    }
}
