package com.example.athis.myapplication;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.athis.myapplication.Base.BaseActivity;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.confirm)
    Button confirm;
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.full_screen)
    Button fullScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        configWindow();
        super.onCreate(savedInstanceState);
    }

    public void configWindow(){
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_web_view;
    }

    @OnClick({R.id.confirm, R.id.full_screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                web.loadUrl(input.getText().toString());
                break;
        }
    }
}
