package com.android.renly.meetingreservation.module.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LaunchActivity extends Activity {
    private static final int WAIT_TIME = 2;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch);
        setCopyRight();
        doPreWrok();
        Observable.timer(WAIT_TIME, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> enterHome());
    }

    /**
     * 做一些启动前的工作
     */
    private void doPreWrok() {
    }

    private void enterHome() {
        startActivity(new Intent(LaunchActivity.this,HomeActivity.class));
        finish();
    }

    //自动续命copyright
    private void setCopyRight() {
        int year = 2016;
        int yearNow = Calendar.getInstance().get(Calendar.YEAR);

        if (year < yearNow) {
            year = yearNow;
        }
        ((TextView) findViewById(R.id.copyright))
                .setText("©2018-" + year + " Chicken Dinner");
    }

    /**
     * startActivity屏蔽物理返回按钮
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void finish() {
        super.finish();
        // 去掉自带的转场动画
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }
}
