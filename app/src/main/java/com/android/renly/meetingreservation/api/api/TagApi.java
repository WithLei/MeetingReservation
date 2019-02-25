package com.android.renly.meetingreservation.api.api;

import com.android.renly.meetingreservation.api.bean.AskMeetingRoom;
import com.android.renly.meetingreservation.api.bean.AskTag;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TagApi {
    @POST("get")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> getTag(@Body AskTag askTag);
}
