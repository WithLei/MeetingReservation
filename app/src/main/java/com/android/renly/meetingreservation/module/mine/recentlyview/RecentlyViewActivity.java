package com.android.renly.meetingreservation.module.mine.recentlyview;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

public class RecentlyViewActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_meetinglist;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "最近浏览");
        initSlidr();
    }
}
