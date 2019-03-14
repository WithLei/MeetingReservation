package com.android.renly.meetingreservation.module.meeting.addPeople;

import android.content.Intent;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.TextView;

import com.android.renly.meetingreservation.App;
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

    private boolean isAnnouncer = false;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_peoplelist;
    }

    @Override
    protected void initData() {
        isAnnouncer = getIntent().getBooleanExtra("isAnnouncer", false);
        initList();
    }

    private void initList() {
        list = new ArrayList<>();
        if (isAnnouncer)
            list.add(new SimpleUser(App.getUserName(), "http://149.28.149.136:8080/image/user1.jpg"));
        else {
            for (int i = 1;i < 10;i++)
                list.add(new SimpleUser("user" + i, "http://149.28.149.136:8080/image/user" + i + ".jpg"));
        }
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
            Intent intent = new Intent(this, AddPeopleActivity.class);
            startActivityForResult(intent, AddPeopleActivity.requestCode);
        });
        return addPeople;
    }

    private void initAdapter() {
        adapter = new UserAdapter(this, list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode) {
                case AddPeopleActivity.requestCode:
                    break;
            }

        }
    }
}
