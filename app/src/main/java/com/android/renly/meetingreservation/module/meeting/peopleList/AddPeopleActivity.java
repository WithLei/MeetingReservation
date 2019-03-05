package com.android.renly.meetingreservation.module.meeting.peopleList;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

public class AddPeopleActivity extends BaseActivity {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_addpeople;
    }

    @Override
    protected void initData() {
        initToolBar(true, "添加成员");
        initSlidr();
    }

    @Override
    protected void initView() {

    }
}
