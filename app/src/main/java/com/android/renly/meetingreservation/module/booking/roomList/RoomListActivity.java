package com.android.renly.meetingreservation.module.booking.roomList;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

public class RoomListActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_rooomlist;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "选择会室");
        initSlidr();
    }
}
