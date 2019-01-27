package com.android.renly.meetingreservation.module.mine.attend;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

public class AttendActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_meetinglist;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "我的参与");
        initSlidr();
    }
}
