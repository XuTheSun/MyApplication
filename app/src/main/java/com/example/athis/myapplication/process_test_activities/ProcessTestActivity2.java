package com.example.athis.myapplication.process_test_activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.R;
import com.example.athis.myapplication.dataBean.cards;
import com.example.athis.myapplication.utils.FilePathUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.ref.WeakReference;

import butterknife.OnClick;

public class ProcessTestActivity2 extends BaseActivity {

    MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new MyHandler(this);
//        readFromFile();
    }

    private void readFromFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cards card = null;
                File cache = new File(FilePathUtils.getCachePath(ProcessTestActivity2.this)
                        + "/card.txt");
                ObjectInputStream in = null;
                try {
                    in = new ObjectInputStream(new FileInputStream(cache));
                    card = (cards) in.readObject();
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = card;
                    handler.handleMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_process_test2;
    }

    @OnClick(R.id.read_from_file)
    public void onViewClicked() {
        readFromFile();
    }

    public static class MyHandler extends Handler {

        WeakReference<ProcessTestActivity2> context;

        public MyHandler(ProcessTestActivity2 activity2) {
            context = new WeakReference<ProcessTestActivity2>(activity2);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                cards card = null;
                if (msg.obj instanceof cards) {
                    card = (cards) msg.obj;
                }
                Log.d("CARD INFORMATION", card.name + ", " + card.goTo);
                Toast.makeText(context.get(), card.name + ", " + card.goTo, Toast.LENGTH_SHORT).show();
            }
        }
    }


}
