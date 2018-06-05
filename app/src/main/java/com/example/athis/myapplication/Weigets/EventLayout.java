package com.example.athis.myapplication.Weigets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zts-pc-375 on 2018/5/4.
 */

public class EventLayout extends LinearLayout {

    boolean returns = false;

    public EventLayout(Context context) {
        super(context);
    }

    public EventLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("activity_corp_img.dispatch",ev.getAction()+"");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("activity_corp_img.onTouchEvent",event.getAction()+"");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                break;
//                return true;
            case MotionEvent.ACTION_DOWN:
                break;
//                return true;
            case MotionEvent.ACTION_UP:
//                Log.d("Layout","UP");
                return true;
        }
        Log.d("activity_corp_img.Intercept",ev.getAction()+"");
        return super.onInterceptTouchEvent(ev);
    }

    public void setReturn(boolean returns){
        this.returns = returns;
    }
}
