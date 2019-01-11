package com.android.renly.meetingreservation.module.booking.success;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

public class BookingSuccessActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_booking_success;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "需求订单详情");
        initSlidr();
    }
}
