package com.android.renly.meetingreservation.api.api;

import com.android.renly.meetingreservation.api.bean.AskService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceApi {

    // 请求服务
    @POST("add")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> addService(@Body AskService askService);
}
