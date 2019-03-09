package com.android.renly.meetingreservation.api.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FaceApi {
    // 从服务器获取特征值
    @GET
    Observable<ResponseBody> getEigenvalues(@Url String url);
}
