package com.android.renly.meetingreservation.module.meeting;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

public class MeetingActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_meeting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "会议信息");
        initSlidr();
    }
}
