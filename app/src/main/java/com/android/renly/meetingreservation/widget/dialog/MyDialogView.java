package com.android.renly.meetingreservation.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.renly.meetingreservation.R;

public class MyDialogView extends Dialog {

    private boolean isCancelable; // 点击其他位置返回
    private boolean isBackCancelable; // 点击back返回
    private View view;
    private Context context;

    public MyDialogView(Context context, View view, boolean isCancelable,boolean isBackCancelable) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.view = view;
        this.isCancelable = isCancelable;
        this.isBackCancelable = isBackCancelable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (view != null)
            setContentView(view);
        setCancelable(isCancelable);
        setCanceledOnTouchOutside(isBackCancelable);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }
}
