package com.example.athis.myapplication.process_test_activities;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import com.example.athis.myapplication.Book;
import com.example.athis.myapplication.IBookManager;
import com.example.athis.myapplication.utils.ProcessUtil;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    private List<Book> mBookList = new ArrayList<>();

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        int processUid = Process.myPid();
        String processName = ProcessUtil.getProcessName(getApplicationContext(),processUid);
        Log.d("Process Info" ,processUid+": "+processName);
        Log.d("Process Info" , ProcessConstants.TEST +"");
        initBook();
    }

    public void initBook(){
        mBookList.add(new Book("Java","1"));
        mBookList.add(new Book("Android","2"));
        mBookList.add(new Book("Python","3"));
        mBookList.add(new Book("C++","4"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new PBinder();
    }

    public class PBinder extends IBookManager.Stub {

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public List<Book> showBookList() throws RemoteException {
            return mBookList;
        }
    }
}
