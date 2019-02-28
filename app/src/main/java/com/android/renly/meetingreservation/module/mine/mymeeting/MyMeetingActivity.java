package com.android.renly.meetingreservation.module.mine.mymeeting;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.MainPageAdapter;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.booking.BookingFrag;
import com.android.renly.meetingreservation.module.folder.FolderFrag;
import com.android.renly.meetingreservation.module.mine.MineFrag;
import com.android.renly.meetingreservation.module.mine.mymeeting.fullscreen.NotStartFrag;
import com.android.renly.meetingreservation.module.mine.mymeeting.fullscreen.OverFrag;
import com.android.renly.meetingreservation.module.mine.mymeeting.fullscreen.ProcessingFrag;
import com.android.renly.meetingreservation.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyMeetingActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private MainPageAdapter adapter;

    private String[] titles = new String[]{"进行中", "未开始", "已结束"};
    private List<BaseFragment> fragments;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_mymeeting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "我的会议");
        initSlidr();

        fragments = new ArrayList<>();
        fragments.add(new NotStartFrag());
        fragments.add(new OverFrag());
        fragments.add(new ProcessingFrag());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        adapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < titles.length; i++)
            tabLayout.getTabAt(i).setText(titles[i]);
    }
}
