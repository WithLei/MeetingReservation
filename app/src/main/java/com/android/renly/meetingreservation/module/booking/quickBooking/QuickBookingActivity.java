package com.android.renly.meetingreservation.module.booking.quickBooking;

import android.os.Bundle;
import android.widget.Button;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.booking.roomList.RoomListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuickBookingActivity extends BaseActivity {
    @BindView(R.id.btn_confirm)
    Button btn;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_quickbooking;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "快速预定");
        initSlidr();
        btn.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
//        jumpToActivity(RoomListActivity.class);
        ToastShort("预约成功，可以在 我的预定 中查看预定结果");
        finishActivity();
    }
}
