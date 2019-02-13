package com.android.renly.meetingreservation.module.meeting.peopleList;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

public class PeopleListActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_peoplelist;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "成员管理");
        initSlidr();
    }
}
