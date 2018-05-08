package com.example.athis.myapplication.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.athis.myapplication.R;
import com.example.athis.myapplication.Weigets.EventLayout;

public class EventTestActivity extends AppCompatActivity {

    Button button1, button2, button3;
    EventLayout eventLayout;

    public void initView(){
        eventLayout = findViewById(R.id.event_layout);
//        eventLayout.setReturn(true);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.button1:
                        Toast.makeText(EventTestActivity.this,"button1",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.button2:
                        Toast.makeText(EventTestActivity.this,"button2",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.button3:
                        Toast.makeText(EventTestActivity.this,"button3",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("OnTouch",event.getAction()+"");
                return false;
            }
        };
        button1.setOnClickListener(listener);
        button1.setOnTouchListener(touchListener);
        button2.setOnClickListener(listener);
        button2.setOnTouchListener(touchListener);
        button3.setOnClickListener(listener);
        button3.setOnTouchListener(touchListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_test);
        initView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("Activity.dispatch",ev.getAction()+"");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("Activity.OnTouchEvent",event.getAction()+"");
        return super.onTouchEvent(event);
    }
}
