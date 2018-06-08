package com.example.athis.myapplication.process_test_activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.FileUriExposedException;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.Book;
import com.example.athis.myapplication.IBookManager;
import com.example.athis.myapplication.R;
import com.example.athis.myapplication.dataBean.cards;
import com.example.athis.myapplication.utils.FilePathUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProcessTestActivity extends BaseActivity {

    IBookManager manager;

    public static final int FLAG_SEND = 0;
    public static final int FLAG_START_SERVICE = 1;
    @BindView(R.id.button_next)
    Button buttonNext;
    @BindView(R.id.button_bind)
    Button button_bind;
    @BindView(R.id.button_add)
    Button buttonAdd;
    @BindView(R.id.button_show)
    Button buttonShow;
    @BindView(R.id.tv_book_list)
    TextView tvBookList;

    List<Book> mBookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProcessConstants.TEST = 100;
//        getList();
//        refreshTv();
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_process_test;
    }

    public void refreshTv(){
        StringBuilder result = new StringBuilder();
        for(Book book: mBookList){
            result.append(book+ "\n");
        }
        tvBookList.setText(result);
    }

    public void addBook(){
        try{
            manager.addBook(new Book("added","1111111"));
            Toast.makeText(this,"添加成功。",Toast.LENGTH_LONG);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getList(){
        try {
            mBookList = manager.showBookList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeToFile(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                cards card = new cards("test file write", 100);
                File cacheFile = new File(FilePathUtils.getCachePath(ProcessTestActivity.this)+"/card.txt");
//                Log.d("FILE PATH:", cacheFile.getPath());
                ObjectOutputStream out = null;
                try{
                    if(cacheFile.exists()){
                        cacheFile.delete();
                    }
                    cacheFile.createNewFile();
                    out = new ObjectOutputStream(new FileOutputStream(cacheFile));
                    out.writeObject(card);
                    Log.d("Finish Writing","Finish!!!");
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.button_next, R.id.button_bind, R.id.button_add, R.id.button_show, R.id.write_to_file})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button_next:
                startActivity(new Intent(this, ProcessTestActivity2.class));
                break;
            case R.id.button_bind:
                intent = new Intent(this, MyService.class);
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.button_add:
                addBook();
                break;
            case R.id.button_show:
                getList();
                refreshTv();
                break;
            case R.id.write_to_file:
                writeToFile();
                break;
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            manager = IBookManager.Stub.asInterface(service);
            Log.d("Connection", "Connected to service");
            Toast.makeText(ProcessTestActivity.this,
                    "Service Connected", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("Connection", "Disconnected");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
