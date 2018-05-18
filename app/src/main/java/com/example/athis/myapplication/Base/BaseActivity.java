package com.example.athis.myapplication.Base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.athis.myapplication.utils.ProcessUtil;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int processUid = Process.myPid();
        String processName = ProcessUtil.getProcessName(getApplicationContext(),processUid);
        Log.d("Process Info" ,processUid+": "+processName);
    }
}
