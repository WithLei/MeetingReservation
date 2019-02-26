package com.android.renly.meetingreservation.utils.upload;

import com.android.renly.meetingreservation.utils.LogUtils;

import io.reactivex.observers.DefaultObserver;

public abstract class FileUploadObserver<T> extends DefaultObserver<T> {

    @Override
    public void onNext(T t) {
        onUpLoadSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onUploadFail(e);
    }

    @Override
    public void onComplete() {

    }

    // 监听进度的改变
    public void onProgressChange(long bytesWritten, long contentLength) {
        onProgress((int)(bytesWritten*100 / contentLength));
    }

    // 上传进度回调
    public abstract void onProgress(int i);

    // 上传失败的回调
    public abstract void onUpLoadSuccess(T t);

    // 上传成功的回调
    public abstract void onUploadFail(Throwable e);
}
