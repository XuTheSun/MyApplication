package com.example.athis.myapplication.process_test_activities;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

public class MessengerService extends Service {

    Messenger server;



    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
