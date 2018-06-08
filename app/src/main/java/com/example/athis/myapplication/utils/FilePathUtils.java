package com.example.athis.myapplication.utils;

import android.content.Context;
import android.os.Environment;

public class FilePathUtils {
    public static String getCachePath(Context context){
        String path = "";
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
            path = context.getExternalCacheDir().getPath();
        }else{
            path = context.getCacheDir().getPath();
        }
        return path;
    }
}
