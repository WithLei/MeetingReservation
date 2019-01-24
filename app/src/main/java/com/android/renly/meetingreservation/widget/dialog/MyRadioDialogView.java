package com.android.renly.meetingreservation.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.renly.meetingreservation.R;

public class MyRadioDialogView extends MyDialogView
        implements View.OnClickListener {
    private View mView = null;

    /**
     * 设置radio点击事件
     * 设置布局点击事件
     */

    public MyRadioDialogView(Context context, boolean isCancelable, boolean isBackCancelable) {
        super(context, null, isCancelable, isBackCancelable);
        mView = View.inflate(context, R.layout.layout_area_dialog, null);
        mView.findViewById(R.id.cancel).setOnClickListener(this);
        mView.findViewById(R.id.confirm).setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                this.cancel();
                break;
            case R.id.confirm:
                this.dismiss();
                break;
        }
    }
}
