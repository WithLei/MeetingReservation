package com.android.renly.meetingreservation.module.folder.upload;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

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
    @BindView(R.id.expandable_layout0)
    ExpandableLayout layout0;
    @BindView(R.id.expandable_layout1)
    ExpandableLayout layout1;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_upload;
    }

    @Override
    protected void initData() {
//        doUpload();
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
        new Timer()
                .schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.incrementProgressBy(1);
                            }
                        });
                    }
                }, 1000, 100);
    }

    private void doUpload() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "meetingReservation", "speaker01.jpg");
        RetrofitService.uploadAvatar(file, new FileUploadObserver<ResponseBody>() {
            @Override
            public void onProgress(int percent) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress.setText(percent + " ");
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

    String path;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                // 用第三方应用打开
                path = uri.getPath();
                progress.setText(path);
                ToastUtils.ToastShort("1.0: " + path);
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                // 4.4之后
                path = PathUtils.getPath(uri);
                progress.setText(path);
                ToastUtils.ToastShort("2.0: " + path);
            } else {
                // 4.4以下版本调用
                path = PathUtils.getRealPathFromURI(uri);
                progress.setText(path);
                ToastUtils.ToastShort("3.0: " + path);
            }
        }
    }

    @OnClick({R.id.expandable_layout0, R.id.expandable_layout1,R.id.progress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.expandable_layout1:
            break;
            case R.id.progress:
                if (layout1.isExpanded()) {
                    layout0.collapse();
                    layout1.collapse();
                } else if (layout0.isExpanded()){
                    layout1.expand();
                }else
                    layout0.expand();
            break;
        }
    }
}
