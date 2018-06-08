package com.example.athis.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.athis.myapplication.Base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class StatusActivity extends BaseActivity {

    @BindView(R.id.full_screen)
    Button fullScreen;
    @BindView(R.id.invisible)
    Button invisible;
    @BindView(R.id.cover)
    Button cover;
    @BindView(R.id.show)
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_status;
    }

    @OnClick({R.id.full_screen, R.id.invisible, R.id.cover, R.id.show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.full_screen:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                break;
            case R.id.invisible:
                getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
                break;
            case R.id.cover:
                WindowManager.LayoutParams attrs = getWindow().getAttributes();
                attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                getWindow().setAttributes(attrs);
                break;
            case R.id.show:
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                break;
        }
    }
}
