package com.example.athis.myapplication.Base;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.athis.myapplication.ProcessTest.ProcessConstants;
import com.example.athis.myapplication.utils.ProcessUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int processUid = Process.myPid();
        String processName = ProcessUtil.getProcessName(getApplicationContext(),processUid);
        Log.d("Process Info" ,processUid+": "+processName);
        Log.d("Process Info" , ProcessConstants.TEST++ +"");
        setContentView(getResLayout());
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null)
            unbinder.unbind();
    }

    public abstract int getResLayout();
}
