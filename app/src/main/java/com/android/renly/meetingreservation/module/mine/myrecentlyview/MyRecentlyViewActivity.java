package com.android.renly.meetingreservation.module.mine.myrecentlyview;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.bean.Demand;
import com.android.renly.meetingreservation.module.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MyRecentlyViewActivity extends BaseActivity {
    private List<Demand>demandList;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_meetinglist;
    }

    @Override
    protected void initData() {
        demandList = new ArrayList<>();
        demandList.add(new Demand("1月11日 13:32", "2019年1月10日", "09:30 - 11:30", 250, 450));
        demandList.add(new Demand("1月23日 13:32", "2019年1月17日", "09:30 - 11:30", 250, 450));
        demandList.add(new Demand("1月1日 13:32", "2019年1月12日", "09:30 - 11:30", 250, 450));
        demandList.add(new Demand("1月3日 13:32", "2019年1月11日", "09:30 - 11:30", 250, 450));
        demandList.add(new Demand("1月7日 13:32", "2019年1月8日", "09:30 - 11:30", 250, 450));

    }

    @Override
    protected void initView() {
        initToolBar(true, "最近浏览");
        initSlidr();
    }
}
