package com.android.renly.meetingreservation.module.booking.bookingroom;

import android.view.View;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

import butterknife.OnClick;

public class BookingRoomActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_booking_rooom;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit:
                finishActivityBottom();
                break;
        }
    }
}
