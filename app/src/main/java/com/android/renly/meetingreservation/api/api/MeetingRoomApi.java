package com.android.renly.meetingreservation.api.api;

import com.android.renly.meetingreservation.api.bean.AskMeeting;
import com.android.renly.meetingreservation.api.bean.AskMeetingRoom;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MeetingRoomApi {
    @POST("update")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> doMeetingRoomUpdate(@Body AskMeetingRoom askMeetingRoom);

    @POST("listEntity")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ResponseBody> getMeetingRoomListEntity(@Body AskMeetingRoom askMeetingRoom);
}
