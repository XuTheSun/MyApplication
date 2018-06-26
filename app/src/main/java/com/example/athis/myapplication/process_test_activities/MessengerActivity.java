package com.example.athis.myapplication.process_test_activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.R;

public class MessengerActivity extends BaseActivity {

    private Messenger mMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_messenger;
    }

    public void bindService(){
        Intent intent = new Intent(MessengerActivity.this, MessengerService.class);
        bindService(intent, sc, MyService.BIND_AUTO_CREATE);
    }

    public void sendMes(){

    }

    public ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
