package com.example.athis.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.event.Event;
import com.example.athis.myapplication.event.TestEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class EventBusActivity extends BaseActivity {

    EventBus bus;
    @BindView(R.id.button_send)
    Button buttonSend;
    @BindView(R.id.button_send2)
    Button buttonSend2;
    @BindView(R.id.button_next)
    Button buttonNext;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().post(new TestEvent("FQFQFQFQFFQFQFQF"));
        EventBus.getDefault().postSticky(new TestEvent("FQFQFQFQFFQFQFQF"));
        registerEventBus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerEventBus();
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_event_bus;
    }

    public void registerEventBus() {
        if (bus == null) {
            bus = EventBus.getDefault();
            bus.register(this);
        }else
            bus.unregister(this);
    }

    @OnClick({R.id.button_send, R.id.button_send2, R.id.button_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_send:
                bus.post(new TestEvent("MESSAGE1"));
                break;
            case R.id.button_send2:
                bus.postSticky(new TestEvent("MESSAGE2"));
                break;
            case R.id.button_next:
                Intent intent = new Intent(this, EventBusTwoActivity.class);
                startActivity(intent);
                break;
        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
//    public void onEventOrder(Event event){
//        if(event.getClass() == TestEvent.class){
//            Log.d("onEventOrder:",Thread.currentThread().getName()+"");
//            textView.setText(event.getMessage());
//        }
//    }
//
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventMain(Event event){
        if(event.getClass() == TestEvent.class){
            Log.d("onEventMain:",Thread.currentThread().getName()+"");
            textView.setText(event.getMessage());
        }
    }

//    @Subscribe(threadMode = ThreadMode.POSTING)
//    public void onEventPosting(Event event){
//        if(event.getClass() == TestEvent.class){
//            Log.d("onEventPosting:",Thread.currentThread().getName()+"");
//            textView.setText(event.getMessage());
//        }
//    }

//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    public void onEventBack(Event event){
//        if(event.getClass() == TestEvent.class){
//            Log.d("onEventBack:",Thread.currentThread().getName()+"");
//            textView.setText(event.getMessage());
//            Log.d("onEventBack:",Thread.currentThread().getName()+"");
//        }
//    }

//    @Subscribe(threadMode = ThreadMode.ASYNC)
//    public void onEventAsync(Event event){
//        if(event.getClass() == TestEvent.class){
//            Log.d("onEventAsync:",Thread.currentThread().getName()+"");
//            textView.setText(event.getMessage());
//        }
//    }
}
