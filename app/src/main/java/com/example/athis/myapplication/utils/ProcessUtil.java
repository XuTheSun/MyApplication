package com.example.athis.myapplication.utils;

import android.app.ActivityManager;
import android.content.Context;

public class ProcessUtil {
    public static String getProcessName(Context context, int uid){
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()){
            if(processInfo.uid == uid){
                return  processInfo.processName;
            }
        }
        return null;
    }
}
