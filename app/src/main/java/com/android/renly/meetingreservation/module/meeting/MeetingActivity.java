package com.android.renly.meetingreservation.module.meeting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.map.MapActivity;
import com.android.renly.meetingreservation.module.meeting.peopleList.PeopleListActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingActivity extends BaseActivity {
    @BindView(R.id.bigImg)
    ImageView img;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_meeting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "会议信息");
        initSlidr();
        Picasso.get()
                .load("http://149.28.149.136:8080/image/room01.jpg")
                .into(img);
    }


    @OnClick({R.id.edit, R.id.ll_people, R.id.location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit:
                break;
            case R.id.ll_people:
                jumpToActivity(PeopleListActivity.class);
                break;
            case R.id.location:
                jumpToActivity(MapActivity.class);
                break;
        }
    }
}
