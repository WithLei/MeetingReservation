package com.android.renly.meetingreservation.api.api;

import com.android.renly.meetingreservation.api.bean.AskUser;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {
    // 根据ID查询用户
    @POST("get")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> getUser(@Body AskUser askUser);

    // 查询预约单下的所有用户
    @POST("listEntityByApplyId")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> getListEntityByApplyId();

    // 根据手机号查询用户
    @POST("getEntityByPhone")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> getEntityByPhone(@Body AskUser askUser);

    // 用户登录
    @POST("login")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> doLogin(@Body AskUser askUser);

    // 用户信息修改
    @POST("update")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> doUserUpdate(@Body AskUser askUser);

    // 用户查询
    @POST("listEntity")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> getListEntity(@Body AskUser askUser);

    // 用户注册
    @POST("register")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> doRegister(@Body AskUser askUser);
}
