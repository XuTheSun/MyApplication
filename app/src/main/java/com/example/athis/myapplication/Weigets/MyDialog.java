package com.example.athis.myapplication.Weigets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.athis.myapplication.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDialog extends Dialog {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.line_divider)
    View lineDivider;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    private String title, mes;
    private OnClickListener onConfirm, onCancel;
    private View view;

    public MyDialog(@NonNull Context context, String title, String mes, final OnClickListener onConfirm) {
        super(context);
        this.title = title;
        this.mes = mes;
        this.onConfirm = onConfirm;
        initView();
        initListener();
    }

    private void initView() {
        com.example.athis.myapplication.utils.ViewUtils.setDialogFullScreen(this);
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_my_dialog, null);
        setContentView(R.layout.layout_my_dialog);
        ButterKnife.bind(this);
        if(!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
        if(!TextUtils.isEmpty(mes)){
            tvMessage.setText(mes);
        }
    }

    private void initListener() {
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirm.onClick(MyDialog.this,1);
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
