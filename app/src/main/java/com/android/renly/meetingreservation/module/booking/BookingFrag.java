package com.android.renly.meetingreservation.module.booking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.acker.simplezxing.activity.CaptureActivity;
import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.booking.quickBooking.QuickBookingActivity;
import com.android.renly.meetingreservation.module.booking.roomList.RoomListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BookingFrag extends BaseFragment {
    @BindView(R.id.qrbooking)
    LinearLayout qrbtn;
    @BindView(R.id.bookingroom)
    LinearLayout roombtn;
    @BindView(R.id.quickbooking)
    LinearLayout quickbtn;
    Unbinder unbinder;

    @Override
    protected void initInjector() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.frag_booking;
    }

    @Override
    protected void initData(Context content) {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void ScrollToTop() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.qrbooking, R.id.bookingroom, R.id.quickbooking})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qrbooking:
                startActivityForResult(new Intent(mContent, CaptureActivity.class), CaptureActivity.REQ_CODE);
                break;
            case R.id.bookingroom:
                jumpToActivity(RoomListActivity.class);
                break;
            case R.id.quickbooking:
                jumpToActivity(QuickBookingActivity.class);
                break;
        }
    }
}
