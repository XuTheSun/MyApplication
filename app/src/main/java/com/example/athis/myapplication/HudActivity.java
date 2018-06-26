package com.example.athis.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.athis.myapplication.Weigets.DrawHookView;
import com.kaopiz.kprogresshud.KProgressHUD;

public class HudActivity extends AppCompatActivity {

    KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hud);
        hud = KProgressHUD.create(this);
        DrawHookView hook = new DrawHookView(this);
//        RelativeLayout rl = new RelativeLayout(this);
//        rl.addView(rl);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(200,200);
        hook.setLayoutParams(params);
//        hook.requestLayout();
        hud.setCustomView(hook);
        hud.show();
    }
}
