package com.android.renly.meetingreservation.module.folder.upload;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.android.renly.meetingreservation.App;
import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.RetrofitService;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.utils.LogUtils;
import com.android.renly.meetingreservation.utils.toast.ToastUtils;
import com.android.renly.meetingreservation.utils.upload.FileUploadObserver;
import com.android.renly.meetingreservation.utils.upload.PathUtils;
import com.daimajia.numberprogressbar.NumberProgressBar;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class UploadActivity extends BaseActivity {
    @BindView(R.id.progress)
    TextView progress;
    @BindView(R.id.progressbar)
    NumberProgressBar progressBar;

    private Timer timer;

    public static final int requestCode = 1024;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_upload;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        initToolBar(true, "上传文件");
        addToolbarMenu(R.drawable.ic_file_upload_white_24dp)
                .setOnClickListener(view -> {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    //intent.setType(“image/*”);//选择图片
                    //intent.setType(“audio/*”); //选择音频
                    //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                    //intent.setType(“video/*;image/*”);//同时选择视频和图片
                    intent.setType("*/*"); // 无类型限制
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(intent, 201);
                });
        initSlidr();
//        timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                progressBar.incrementProgressBy(1);
//                            }
//                        });
//                    }
//                }, 1000, 100);
    }

    private void doUpload(String path) {
        File file = new File(path);
        RetrofitService.uploadFile(file, 2, (int)App.getUserUidKey(),
                new FileUploadObserver<ResponseBody>() {
            @Override
            public void onProgress(int percent) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress.setText(percent + " ");
                        LogUtils.printLog("percent = " + percent + "%");
                    }
                });
            }

            @Override
            public void onUpLoadSuccess(ResponseBody responseBody) {
                ToastUtils.ToastShort("上传成功");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            progress.setText(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onUploadFail(Throwable e) {
                ToastUtils.ToastShort("上传失败 " + e.getMessage());
                LogUtils.printError(e.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path;
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                // 用第三方应用打开
                path = uri.getPath();
                doUpload(path);
//                ToastUtils.ToastShort("1.0: " + path);
                return;
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                // 4.4之后
                path = PathUtils.getPath(uri);
//                ToastUtils.ToastShort("2.0: " + path);
            } else {
                // 4.4以下版本调用
                path = PathUtils.getRealPathFromURI(uri);
//                ToastUtils.ToastShort("3.0: " + path);
            }
            doUpload(path);
        }
    }

    @OnClick({R.id.progress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.progress:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
