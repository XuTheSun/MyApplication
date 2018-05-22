package com.example.athis.myapplication.process_test_activities;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.Book;
import com.example.athis.myapplication.R;

import java.util.List;

public class ProcessTestActivity2 extends BaseActivity {

//    private PBinder mPBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_process_test2;
    }

//    public class PBinder extends IBookManager.Stub {
//        @Override
//        public void addBook() throws RemoteException {
//
//        }
//
//        @Override
//        public List<Book> showBookList() throws RemoteException {
//            return null;
//        }
//    }
}
