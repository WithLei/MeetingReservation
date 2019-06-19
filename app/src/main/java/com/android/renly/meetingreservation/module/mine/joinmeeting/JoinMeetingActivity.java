package com.android.renly.meetingreservation.module.mine.joinmeeting;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.webview.WebViewActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class JoinMeetingActivity extends BaseActivity {
    @BindView(R.id.join)
    Button join;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_join;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "加入会议");
        initSlidr();

        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> showSoftInput());
    }

    @OnClick({R.id.join})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.join:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("url","http://blog.graydove.cn/other/");
                intent.putExtra("title", "会议内容");
                startActivity(intent);
                break;
        }
    }
}
