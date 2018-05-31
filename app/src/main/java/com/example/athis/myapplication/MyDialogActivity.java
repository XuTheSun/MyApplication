package com.example.athis.myapplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.Weigets.MyDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class MyDialogActivity extends BaseActivity {

    MyDialog dialog;
    @BindView(R.id.btm_show)
    Button btmShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_main2;
    }

    public void initDialog() {
        dialog = new MyDialog(this, "NIHAO", "FQFQFQFQFQ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyDialogActivity.this, "asdasdasd", Toast.LENGTH_LONG).show();
            }
        });

        Point point = new Point();
        dialog.getWindow().getWindowManager().getDefaultDisplay().getSize(point);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams mWindowAttributes = dialog.getWindow().getAttributes();
        mWindowAttributes.dimAmount = 0f;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
//        mWindowAttributes.width = WindowManager.LayoutParams.MATCH_PARENT;
//        mWindowAttributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(mWindowAttributes);
    }

    @OnClick(R.id.btm_show)
    public void onViewClicked() {
        dialog.show();
    }
}
