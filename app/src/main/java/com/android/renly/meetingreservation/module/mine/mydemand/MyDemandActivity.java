package com.android.renly.meetingreservation.module.mine.mydemand;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.DemandAdapter;
import com.android.renly.meetingreservation.api.bean.Demand;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDemandActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<Demand> demandList;
    private DemandAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_meetinglist;
    }

    @Override
    protected void initData() {
        demandList = new ArrayList<>();
        demandList.add(new Demand("3月23日 13:32", "2019年3月25日", "09:30 - 11:30", 45, 450));
        demandList.add(new Demand("3月21日 09:17", "2019年4月7日", "13:30 - 17:30", 88, 1000));
        demandList.add(new Demand("3月15日 13:32", "2019年4月12日", "13:00 - 11:30", 25, 320));
        demandList.add(new Demand("3月3日 13:32", "2019年1月11日", "09:30 - 11:30", 250, 450));
        demandList.add(new Demand("3月7日 13:32", "2019年1月8日", "09:30 - 11:30", 250, 450));

        initAdapter();
    }

    /**
     * 初始化recylerView的一些属性
     */
    protected RecyclerView.LayoutManager mLayoutManager;

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 20, getResources().getColor(R.color.colorDivider)));
        // 调整draw缓存,加速recyclerview加载
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    private void initAdapter() {
        adapter = new DemandAdapter(this, demandList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        initToolBar(true, "需求订单");
        initSlidr();
        initRecyclerView();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
