package com.android.renly.meetingreservation.module.folder.upload;

import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.RetrofitService;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.utils.LogUtils;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class UploadActivity extends BaseActivity {
    @BindView(R.id.progress)
    TextView progress;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_upload;
    }

    @Override
    protected void initData() {
        RetrofitService.doLogin("phone", "13373814211", "qqq1201")
                .subscribe(responseBody -> progress.setText(responseBody.string()),
                        throwable -> LogUtils.printError(throwable.getMessage()));
    }

    @Override
    protected void initView() {
        initToolBar(true, "上传文件");
        initSlidr();
    }
}
