package com.android.renly.meetingreservation.module.mine.joinmeeting;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class JoinMeetingActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_join;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "加入会议");
        initSlidr();

        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> showSoftInput());
    }
}
