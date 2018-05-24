package com.example.athis.myapplication.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AssetUtil {
    public static String getFileFromAsset(Context context){
        try {
            InputStreamReader reader = new InputStreamReader(context.getResources().getAssets().open("categories.txt"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            StringBuilder result = new StringBuilder();
            while((line = bufferedReader.readLine()) != null){
                result.append(line);
            }
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
