package com.android.renly.meetingreservation.module.booking.roomArrangement;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

public class RoomArrangement extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_roomarrangement;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initSlidr();
    }
}
