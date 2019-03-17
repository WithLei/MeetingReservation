package com.android.renly.meetingreservation.module.meeting.addPeople;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.MainPageAdapter;
import com.android.renly.meetingreservation.adapter.SortAdapter;
import com.android.renly.meetingreservation.api.bean.SortModel;
import com.android.renly.meetingreservation.listener.PinyinComparator;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.meeting.addPeople.department.DepartmentFrag;
import com.android.renly.meetingreservation.module.meeting.addPeople.name.NameFrag;
import com.android.renly.meetingreservation.module.meeting.peopleList.PeopleListActivity;
import com.android.renly.meetingreservation.utils.CharacterParser;
import com.android.renly.meetingreservation.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddPeopleActivity extends BaseActivity {
    @BindView(R.id.num)
    TextView peopleNum;
    @BindView(R.id.check)
    TextView check; // 多选
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    public static final int requestCode = 2048;

    public boolean isNeedChecked; // 是否需要出现选择的按钮

    private int selectedNum = 0;

    private List<BaseFragment> fragments;
    private MainPageAdapter mainPageAdapter;
    private DepartmentFrag departmentFrag;
    private NameFrag nameFrag;

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
        fragments = new ArrayList<>();
        nameFrag = new NameFrag();
        departmentFrag = new DepartmentFrag();
        fragments.add(departmentFrag);
        fragments.add(nameFrag);
        viewPager.setOffscreenPageLimit(2);
        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mainPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 更新选择人数
     */
    public void updateSelectedNum(int plus) {
        selectedNum += plus;
        peopleNum.setText(selectedNum + "");
    }

    @OnClick({R.id.check, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check:
                if (isNeedChecked) {
                    nameFrag.setNeedCheck(false);
                    departmentFrag.setNeedCheck(false);
                    check.setText("选择");
                    isNeedChecked = false;
                } else {
                    nameFrag.setNeedCheck(true);
                    departmentFrag.setNeedCheck(true);
                    check.setText("取消");
                    isNeedChecked = true;
                }
                nameFrag.notifyDataSetChanged();
                departmentFrag.notifyDataSetChanged();
                break;
            case R.id.confirm:
                Intent intent = new Intent(this, PeopleListActivity.class);
                intent.putExtra("people",5);
                setResult(RESULT_OK, intent);
                finishActivity();
                break;
        }
    }
}
