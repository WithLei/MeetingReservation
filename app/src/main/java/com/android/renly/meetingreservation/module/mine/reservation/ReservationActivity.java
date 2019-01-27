package com.android.renly.meetingreservation.module.mine.reservation;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

public class ReservationActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_meetinglist;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "我的预定");
        initSlidr();
    }
}
