package com.android.renly.meetingreservation.module.meeting;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.folder.upload.UploadActivity;
import com.android.renly.meetingreservation.module.map.MapActivity;
import com.android.renly.meetingreservation.module.meeting.edit.EditMeetingActivity;
import com.android.renly.meetingreservation.module.meeting.addPeople.PeopleListActivity;
import com.android.renly.meetingreservation.utils.IntentUtils;
import com.android.renly.meetingreservation.utils.network.NetConfig;
import com.android.renly.meetingreservation.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class MeetingActivity extends BaseActivity {
    @BindView(R.id.bigImg)
    ImageView img;
    @BindView(R.id.share)
    RelativeLayout share;
    @BindView(R.id.meeting_title)
    TextView title;
    @BindView(R.id.upload)
    TextView upload;
    @BindView(R.id.avatar1)
    CircleImageView avatar1;
    @BindView(R.id.avatar2)
    CircleImageView avatar2;
    @BindView(R.id.avatar3)
    CircleImageView avatar3;
    @BindView(R.id.avatar4)
    CircleImageView avatar4;
    @BindView(R.id.avatar5)
    CircleImageView avatar5;

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
        Picasso.get()
                .load( "http://149.28.149.136:8080/image/user1.jpg")
                .into(avatar1);
        Picasso.get()
                .load("http://149.28.149.136:8080/image/user2.jpg")
                .into(avatar2);
        Picasso.get()
                .load("http://149.28.149.136:8080/image/user3.jpg")
                .into(avatar3);
        Picasso.get()
                .load("http://149.28.149.136:8080/image/user4.jpg")
                .into(avatar4);
        Picasso.get()
                .load("http://149.28.149.136:8080/image/user5.jpg")
                .into(avatar5);
    }


    @Override
    protected void onResume() {
        super.onResume();
        hideKeyBoard();
    }

    @OnClick({R.id.edit, R.id.ll_people, R.id.location, R.id.share, R.id.upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit:
                jumpToActivityBottom(EditMeetingActivity.class);
                break;
            case R.id.ll_people:
                jumpToActivity(PeopleListActivity.class);
                break;
            case R.id.location:
                jumpToActivity(MapActivity.class);
                break;
            case R.id.share:
                String data = "邀请你加入这场会议：" + title.getText().toString().trim() + "\n" + NetConfig.BASE_MEETING_URL;
                IntentUtils.shareApp(this, data);
                break;
            case R.id.upload:
                jumpToActivity(UploadActivity.class);
                break;
        }
    }
}
