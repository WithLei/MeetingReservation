package com.android.renly.meetingreservation.module.mine.attend;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.CommonAdapter;
import com.android.renly.meetingreservation.api.bean.Demand;
import com.android.renly.meetingreservation.module.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttendActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<Demand> demandList;
    private CommonAdapter adapter;

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

        initAdapter();
    }

    private void initAdapter() {
        adapter = new CommonAdapter(this, demandList);
        printLog("setAdapter");
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        initToolBar(true, "我的参与");
        initSlidr();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
