package com.android.renly.meetingreservation.api.api;

import android.graphics.Bitmap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface FaceApi {
    // 从服务器获取特征值
    @GET
    Observable<ResponseBody> getEigenvalues(@Url String url);

    @GET
    @Streaming
    Observable<ResponseBody>getFaceImg(@Url String url);
}
