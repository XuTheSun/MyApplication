package com.example.athis.myapplication.DragView;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.R;
import com.example.athis.myapplication.adapters.BaseAdapter;
import com.example.athis.myapplication.adapters.BaseViewHolder;
import com.example.athis.myapplication.dataBean.cards;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DragActivity extends BaseActivity {

    @BindView(R.id.list_drag)
    RecyclerView listDrag;

    DragListAdapter adapter;
    List<cards> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initRec();
    }

    public void initRec(){
        adapter = new DragListAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        listDrag.setLayoutManager(gridLayoutManager);
        adapter.setListData(data);
        listDrag.setAdapter(adapter);
        final ItemTouchHelper listHelper = new ItemTouchHelper(new MyListCallBack());
        listHelper.attachToRecyclerView(listDrag);
        adapter.setOnLongClickListener(new BaseAdapter.onLongClickListener<cards>() {
            @Override
            public void onLongClick(BaseViewHolder<cards> holder, cards item, int index) {
                if(index != data.size() -1){
                    listHelper.startDrag(holder);
                }
            }
        });
    }

    public void initData(){
        data.add(new cards("1"));
        data.add(new cards("2"));
        data.add(new cards("3"));
        data.add(new cards("4"));
        data.add(new cards("5"));
        data.add(new cards("6"));
        data.add(new cards("7"));
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_grag;
    }

    public class MyListCallBack extends ItemTouchHelper.Callback {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int moveFlag;
            if(recyclerView.getLayoutManager() instanceof GridLayoutManager){
                moveFlag = ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.DOWN
                        | ItemTouchHelper.RIGHT;
            }else{
                moveFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(moveFlag, ItemTouchHelper.LEFT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int from = viewHolder.getAdapterPosition();
            int to = target.getAdapterPosition();
            if(to != data.size() - 1){
                cards temp = data.remove(from);
                data.add(to, temp);
                adapter.notifyItemMoved(from, to);
            }
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }
    }
}
