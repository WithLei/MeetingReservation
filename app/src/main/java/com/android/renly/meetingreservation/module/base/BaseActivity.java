package com.android.renly.meetingreservation.module.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.utils.DimmenUtils;
import com.android.renly.meetingreservation.utils.toast.MyToast;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

import java.io.File;
import java.io.RandomAccessFile;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends FragmentActivity {
    /***是否显示标题栏*/
    private boolean isshowtitle = true;
    /***是否显示标题栏*/
    private boolean isshowstate = false;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        unbinder = ButterKnife.bind(this);
        // 初始化状态栏
//        initWindowTitle();
        // 设置数据
        initData();
        // 初始化控件
        initView();
    }

    protected abstract int getLayoutID();

    protected abstract void initData();

    protected abstract void initView();

    public void ToastLong(String msg) {
        MyToast.showText(this, msg, Toast.LENGTH_LONG);
    }

    public void ToastShort(String msg) {
        MyToast.showText(this, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 初始化状态栏
     */
    private void initWindowTitle() {
        if (!isshowtitle) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        if (isshowstate) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 是否设置标题栏
     *
     * @return
     */
    public void setTitle(boolean ishow) {
        isshowtitle = ishow;
    }

    /**
     * 设置是否显示状态栏
     *
     * @param ishow
     */
    public void setState(boolean ishow) {
        isshowstate = ishow;
    }

    /**
     * Log输出
     * error
     * Filter:print
     *
     * @param str
     */
    public void printLog(String str) {
        Log.e("print", str);
    }

    /**
     * 打印str到手机src内存中
     *
     * @param src 地址
     * @param str 打印内容
     */
    public void writeData(String src, String str) {
        try {
            File file = new File(src);
            if (!file.exists()) {
                printLog("Create the file:" + src);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
//            raf.seek(file.length());
            raf.write(str.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

    /**
     * 打开targetActivity
     *
     * @param targetActivity
     */
    public void gotoActivity(Class<?> targetActivity) {
        startActivity(new Intent(this, targetActivity));
        overridePendingTransition(R.anim.translate_in, R.anim.translate_out);
    }

    /**
     * 初始化标题栏
     *
     * @param isshowBack
     * @param text
     */
    protected void initToolBar(boolean isshowBack, String text) {
        View toolbar = findViewById(R.id.myToolBar);
        if (toolbar != null) {
            ((TextView) toolbar.findViewById(R.id.tv_toolbar_title)).setText(text);
            if (isshowBack) {
                findViewById(R.id.iv_toolbar_back).setOnClickListener(view -> finishActivity());
            } else {
                findViewById(R.id.iv_toolbar_back).setVisibility(View.GONE);
            }
        }
    }

    /**
     * 添加标题栏组件
     *
     * @param resid
     * @return
     */
    protected ImageView addToolbarMenu(int resid) {
        View toolbar = findViewById(R.id.myToolBar);
        if (toolbar != null) {
            ImageView i = toolbar.findViewById(R.id.iv_toolbar_menu);
            i.setImageResource(resid);
            i.setVisibility(View.VISIBLE);
            return i;
        }
        return null;
    }

    /**
     * 添加标题栏组件
     *
     * @param v
     */
    protected void addToolbarView(View v) {
        FrameLayout toolbar = findViewById(R.id.myToolBar);
        if (toolbar != null) {
            FrameLayout.LayoutParams pls = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            v.setLayoutParams(pls);
            int padding = DimmenUtils.dip2px(this, 12);
            v.setPadding(padding, padding, padding, padding);
            pls.setMarginEnd(padding);
            pls.gravity = Gravity.END;
            toolbar.addView(v);
        }
    }

    public void hideKeyBoard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void showSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void finishActivity() {
        finish();
        overridePendingTransition(R.anim.translate_in, R.anim.translate_out);
    }

    /**
     * 初始化滑动事件
     */
    public void initSlidr() {
        SlidrConfig config = new SlidrConfig.Builder()
                .position(SlidrPosition.LEFT)//滑动起始方向
                .edge(true)
                .edgeSize(0.18f)//距离左边界占屏幕大小的18%
                .build();
        Slidr.attach(this, config);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.translate_in, R.anim.translate_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
