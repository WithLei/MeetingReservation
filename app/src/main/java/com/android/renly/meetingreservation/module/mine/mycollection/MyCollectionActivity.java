package com.android.renly.meetingreservation.module.mine.mycollection;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

public class MyCollectionActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_meetinglist;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "我的收藏");
        initSlidr();
    }
}
