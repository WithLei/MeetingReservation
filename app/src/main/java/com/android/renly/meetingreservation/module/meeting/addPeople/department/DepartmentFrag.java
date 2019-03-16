package com.android.renly.meetingreservation.module.meeting.addPeople.department;

import android.content.Context;

import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.meeting.addPeople.AddPeopleActivity;

public class DepartmentFrag extends BaseFragment {
    @Override
    protected void initInjector() {

    }

    @Override
    public int getLayoutid() {
        return 0;
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

    private AddPeopleActivity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AddPeopleActivity) context;
    }
}
