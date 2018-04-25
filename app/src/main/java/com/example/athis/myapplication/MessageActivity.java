package com.example.athis.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.athis.myapplication.adapters.BaseAdapter;
import com.example.athis.myapplication.adapters.MessageAdapter;
import com.example.athis.myapplication.dataBean.message;
import com.example.athis.myapplication.utils.LogUtils;
import com.example.athis.myapplication.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    List<message> data= new ArrayList();
    MessageAdapter adapter;
    RelativeLayout root;

//    是否允许软键盘弹出
    boolean popAble = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initData();
        initRecycler();
        initActivityContent();
    }

    public void initRecycler(){
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageAdapter();
        adapter.setListData(data);
        adapter.setOnLongClickListener(new BaseAdapter.onLongClickListener<message>() {
            @Override
            public void onLongClick(message item, int index) {
                LogUtils.d(item.content + " : "+ index);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void initActivityContent(){
        Button btn_confrim = findViewById(R.id.btn_confirm);
        final EditText ed_message = findViewById(R.id.et_message);
        btn_confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("count: ",data.size()+"");
                Log.d("Height and Width: ", recyclerView.getWidth() + " ," + recyclerView.getHeight());
                String msg = ed_message.getText().toString();
                if(msg.equals("") || msg == null)
                    return;
                adapter.addItem(new message(msg, message.STATUS_SEND));
                recyclerView.smoothScrollToPosition(data.size()-1);
                Log.d("count",data.size()+"");
                ed_message.setText("");
            }
        });
        ed_message.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    popAble = true;
                }else{
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        /*
        * 通过监听根布局layout变化来实现监听软键盘弹出事件
        * */
        root = findViewById(R.id.root);
        root.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.d("Layout Change: ", "l,t,r,b " + left + " " + top+" "+ right+" "+ bottom);
                Log.d("Layout Change: ", "l,t,r,b " + oldLeft + " " + oldTop+" " + oldRight+" " + oldBottom);
                if((bottom - top) > (oldTop - oldBottom)/3 && popAble){
//                    Toast.makeText(MessageActivity.this,"Keyboard Pop",Toast.LENGTH_SHORT).show();
                    recyclerView.smoothScrollToPosition(data.size() - 1);
                }else{
//                    Toast.makeText(MessageActivity.this,"Keyboard UnPop",Toast.LENGTH_SHORT).show();
                }
                popAble = false;
            }
        });
    }

    public void initData(){
        data.add(new message("Hello",message.STATUS_RECEIVE));
        data.add(new message("Hello",message.STATUS_SEND));
        data.add(new message("How are you",message.STATUS_RECEIVE));
        data.add(new message("Fine thank you, and you",message.STATUS_SEND));
        data.add(new message("I'm fine to",message.STATUS_RECEIVE));
        data.add(new message("Great",message.STATUS_SEND));
        data.add(new message("Hello",message.STATUS_RECEIVE));
        data.add(new message("Hello",message.STATUS_SEND));
        data.add(new message("How are you",message.STATUS_RECEIVE));
        data.add(new message("Fine thank you, and you",message.STATUS_SEND));
        data.add(new message("I'm fine to",message.STATUS_RECEIVE));
        data.add(new message("Great",message.STATUS_SEND));
        data.add(new message("Hello",message.STATUS_RECEIVE));
        data.add(new message("Hello",message.STATUS_SEND));
        data.add(new message("How are you",message.STATUS_RECEIVE));
        data.add(new message("Fine thank you, and you",message.STATUS_SEND));
        data.add(new message("I'm fine to",message.STATUS_RECEIVE));
        data.add(new message("Great",message.STATUS_SEND));
    }

    /*
    * 通过点击editview使软键盘弹出时，点击屏幕其他地方使软键盘缩回
    * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            View current = getCurrentFocus();
            if(hasInputMethod(current, ev)){
                if(operateInput(current, false))
                    return true;
            };
        }
        return super.dispatchTouchEvent(ev);
    }

//    判断点击的位置，在edittext所在布局的话没有变化，反之则隐藏软键盘
    public boolean hasInputMethod(View view, MotionEvent event){
        if(view instanceof EditText){
            View parent = findViewById(R.id.ll_et);
            int[] leftTop = {0,0};
            view.getLocationInWindow(leftTop);
            int left = leftTop[0],top = leftTop[1], right = left + parent.getWidth(), bottom = top + parent.getHeight();
            if(event.getX() > left && event.getX()< right &&
                    event.getY() > top && event.getY()< bottom){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }

//    控制软键盘弹出，缩回
    public boolean operateInput(View view, boolean show){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        if(imm != null)
            if(show)
                return imm.showSoftInput(view,0);
            else{
                view.clearFocus();
                return imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        return false;
    }
}
