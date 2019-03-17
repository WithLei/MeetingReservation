package com.android.renly.meetingreservation.module.meeting.edit;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.meeting.MeetingActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class EditMeetingActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.meeting_title)
    EditText meetingTitle;
    @BindView(R.id.meeting_intro)
    EditText meetingIntro;

    public static final int requestCode = 314;

    private String title;
    private String intro;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_meeting;
    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
        intro = getIntent().getStringExtra("intro");
    }

    @Override
    protected void initView() {
        tvTitle.setText("编辑会议信息");
        addToolbarView(getConfirmView());
        initSlidr();
        if (title.equals("未命名会议"))
            meetingTitle.setHint(title);
        else
            meetingTitle.setText(title);
        if (intro.equals("会议简介信息空空如也~"))
            meetingIntro.setHint(intro);
        else
            meetingIntro.setText(intro);
    }

    private TextView getConfirmView() {
        TextView confrim = new TextView(this);
        confrim.setText("确认");
        confrim.setTextSize(16);
        confrim.setTextColor(getResources().getColor(R.color.white));
        confrim.setGravity(Gravity.CENTER);
        confrim.setOnClickListener(view -> {
            Intent intent = new Intent(this, MeetingActivity.class);
            intent.putExtra("title", meetingTitle.getText().toString());
            intent.putExtra("intro", meetingIntro.getText().toString());
            setResult(RESULT_OK, intent);
            finishActivityBottom();
        });
        return confrim;
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finishActivityBottom();
                break;
        }
    }
}
