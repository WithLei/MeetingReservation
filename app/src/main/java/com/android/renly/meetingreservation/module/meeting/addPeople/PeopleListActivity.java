package com.android.renly.meetingreservation.module.meeting.addPeople;

import android.view.Gravity;
import android.widget.GridView;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.UserAdapter;
import com.android.renly.meetingreservation.api.bean.SimpleUser;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.meeting.peopleList.AddPeopleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PeopleListActivity extends BaseActivity {
    @BindView(R.id.gridView)
    GridView gridView;

    private List<SimpleUser>list;
    private UserAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_peoplelist;
    }

    @Override
    protected void initData() {
        initList();
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 1;i < 10;i++)
            list.add(new SimpleUser("user" + i, "http://149.28.149.136:8080/image/user" + i + ".jpg"));
//        list.add(new SimpleUser("添加成员", ""));
    }

    @Override
    protected void initView() {
        initToolBar(true, "成员管理");
        addToolbarView(getAddPeopleView());
        initSlidr();
        initAdapter();
    }

    private TextView getAddPeopleView() {
        TextView addPeople = new TextView(this);
        addPeople.setText("添加");
        addPeople.setTextSize(16);
        addPeople.setTextColor(getResources().getColor(R.color.white));
        addPeople.setGravity(Gravity.CENTER);
        addPeople.setOnClickListener(view -> {
            jumpToActivity(AddPeopleActivity.class);
        });
        return addPeople;
    }

    private void initAdapter() {
        adapter = new UserAdapter(this, list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
