package com.android.renly.meetingreservation.module.meeting.edit;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class EditMeetingActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;

    public static final int requestCode = 314;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_meeting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        title.setText("编辑会议信息");
        addToolbarView(getConfirmView());
        initSlidr();
    }

    private TextView getConfirmView() {
        TextView confrim = new TextView(this);
        confrim.setText("确认");
        confrim.setTextSize(16);
        confrim.setTextColor(getResources().getColor(R.color.white));
        confrim.setGravity(Gravity.CENTER);
        confrim.setOnClickListener(view -> {
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
