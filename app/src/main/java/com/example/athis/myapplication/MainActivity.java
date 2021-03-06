package com.example.athis.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.DragView.DragActivity;
import com.example.athis.myapplication.MediaPlayer.MediaPlayActivity;
import com.example.athis.myapplication.RecyclerInteract.DoubleRecActivity;
import com.example.athis.myapplication.adapters.BaseAdapter;
import com.example.athis.myapplication.adapters.BaseViewHolder;
import com.example.athis.myapplication.adapters.MainAdapter;
import com.example.athis.myapplication.dataBean.cards;
import com.example.athis.myapplication.process_test_activities.ProcessTestActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    RecyclerView recyclerView;
    MainAdapter adapter;
    List<cards> data = new ArrayList<>();
    public static final int GOTO_MESSAGE = 0;
    public static final int GOTO_EVENT = 1;
    public static final int GOTO_PROCESS = 2;
    public static final int GOTO_REC = 3;
    public static final int GOTO_DIALOG = 4;
    public static final int GOTO_EVENT_BUS = 5;
    public static final int GOTO_DRAG = 6;
    public static final int GOTO_WEB = 7;
    public static final int GOTO_STATUS = 8;
    public static final int GOTO_MEDIA = 9;
    public static final int GOTO_HUD =  10;

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
                    case GOTO_REC:
                        classType = DoubleRecActivity.class;
                        break;
                    case GOTO_DIALOG:
                        classType = MyDialogActivity.class;
                        break;
                    case GOTO_EVENT_BUS:
                        classType = EventBusActivity.class;
                        break;
                    case GOTO_DRAG:
                        classType = DragActivity.class;
                        break;
                    case GOTO_WEB:
                        classType = WebViewActivity.class;
                        break;
                    case GOTO_STATUS:
                        classType = StatusActivity.class;
                        break;
                    case GOTO_MEDIA:
                        classType = MediaPlayActivity.class;
                        break;
                    case GOTO_HUD:
                        classType = HudActivity.class;
                        break;
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
        data.add(new cards("Recycler交互",GOTO_REC));
        data.add(new cards("自定义dialog测试",GOTO_DIALOG));
        data.add(new cards("EventBus",GOTO_EVENT_BUS));
        data.add(new cards("Recycler Drag", GOTO_DRAG));
        data.add(new cards("WebView X5",GOTO_WEB));
        data.add(new cards("Status", GOTO_STATUS));
        data.add(new cards("Media Player", GOTO_MEDIA));
        data.add(new cards("Hud Animation Test", GOTO_HUD));
    }
}
