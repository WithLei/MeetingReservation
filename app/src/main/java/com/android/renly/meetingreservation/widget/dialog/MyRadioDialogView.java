package com.android.renly.meetingreservation.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.utils.LogUtils;

public class MyRadioDialogView extends MyDialogView
        implements View.OnClickListener {
    private View mView = null;
    private onBtnListener listener = null;
    private RadioGroup radioGroup;

    /**
     * 设置radio点击事件
     * 设置布局点击事件
     */

    public MyRadioDialogView(Context context, View view, boolean isCancelable, boolean isBackCancelable) {
        super(context, null, isCancelable, isBackCancelable);
        mView = view;
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

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.check(radioGroup.getChildAt(10).getId());
        radioGroup.scrollBy(10,10);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                this.cancel();
                break;
            case R.id.confirm:
                String checkedText = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                this.listener.onConfirmClick(checkedText);
                this.cancel();
                break;
        }
    }

    public interface onBtnListener {
        void onConfirmClick(String checkedText);
    }

    public void setOnBtnListener(onBtnListener listener) {
        this.listener = listener;
    }
}
