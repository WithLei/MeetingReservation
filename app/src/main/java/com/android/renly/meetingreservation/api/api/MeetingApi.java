package com.android.renly.meetingreservation.api.api;

import com.android.renly.meetingreservation.api.bean.AskMeeting;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MeetingApi {
    @POST("listByAttendWorker")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> getListByAttendWorker(@Body AskMeeting askMeeting);

    @POST("addAttenderWorker")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> addAttenderWorker(@Body AskMeeting askMeeting);

    @POST("deleteAttenderWorker")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> deleteAttenderWorker(@Body AskMeeting askMeeting);

    @POST("listEntity")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> getListEntity(@Body AskMeeting askMeeting);

    @POST("update")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> doMeetingUpdate(@Body AskMeeting askMeeting);
}
