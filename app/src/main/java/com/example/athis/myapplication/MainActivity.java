package com.example.athis.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.adapters.BaseAdapter;
import com.example.athis.myapplication.adapters.BaseViewHolder;
import com.example.athis.myapplication.adapters.MainAdapter;
import com.example.athis.myapplication.adapters.MainViewHolder;
import com.example.athis.myapplication.dataBean.cards;
import com.example.athis.myapplication.process_test_activities.ProcessTestActivity;
import com.example.athis.myapplication.utils.EventTestActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    RecyclerView recyclerView;
    MainAdapter adapter;
    List<cards> data = new ArrayList<>();
    public static final int GOTO_MESSAGE = 0;
    public static final int GOTO_EVENT = 1;
    public static final int GOTO_PROCESS = 2;
    public static final int GOTO_SERVICE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initRecycler();
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_main;
    }

    public void initRecycler(){
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter();
        adapter.setListData(data);
        adapter.setOnClickListener(new BaseAdapter.onClickListener<cards>() {
            @Override
            public void onClick(BaseViewHolder<cards> holder, cards item, int index) {
                Class<?> classType = null;
                switch(item.goTo){
                    case GOTO_MESSAGE:
                        classType = MessageActivity.class;
                        break;
                    case GOTO_EVENT:
                        classType = EventTestActivity.class;
                        break;
                    case GOTO_PROCESS:
                        classType = ProcessTestActivity.class;
                        break;
//                    case GOTO_SERVICE:
//                        classType =
                }
                Intent intent = new Intent(MainActivity.this, classType);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void initData(){
        data.add(new cards("仿聊天界面" , GOTO_MESSAGE));
        data.add(new cards("Event传递测试", GOTO_EVENT));
        data.add(new cards("多进程IPC测试",GOTO_PROCESS));
//        data.add(new cards("Service Test",GOTO_SERVICE));
//        data.add(new cards("C#"));
//        data.add(new cards("C++"));
//        data.add(new cards("Spark"));
//        data.add(new cards("Ruby"));
//        data.add(new cards("Android"));
    }
}
