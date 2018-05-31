package com.example.athis.myapplication.utils;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.WindowManager;

public class ViewUtils {

    public static void setDialogFullScreen(Dialog dialog){
        Point point = new Point();
        dialog.getWindow().getWindowManager().getDefaultDisplay().getSize(point);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams mWindowAttributes = dialog.getWindow().getAttributes();
        mWindowAttributes.dimAmount = 0f;
        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
//        mWindowAttributes.width = point.x;
        mWindowAttributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowAttributes.height = WindowManager.LayoutParams.MATCH_PARENT;
    }
}
