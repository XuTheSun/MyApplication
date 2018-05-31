package com.example.athis.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.event.Event;
import com.example.athis.myapplication.event.TestEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class EventBusTwoActivity extends BaseActivity {

    @BindView(R.id.button_send2)
    Button buttonSend2;
    @BindView(R.id.button_back)
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_event_bus_two;
    }

    @OnClick({R.id.button_send2, R.id.button_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_send2:
                new Thread("POSTING"){
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new TestEvent("Message from second activity."));
                    }
                }.start();
                break;
            case R.id.button_back:
                finish();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(Event event){
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_LONG).show();
    }
}
