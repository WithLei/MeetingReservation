package com.android.renly.meetingreservation.api.api;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UploadApi {

    @Multipart
    @POST("uploadHeadImage")
    Observable<ResponseBody> uploadAvatar(@Part MultipartBody.Part file, @Query("id")int id);

    @Multipart
    @POST("uploadFileToApply")
    Observable<ResponseBody> uploadFileToApply(@Part MultipartBody.Part file,
                                               @Query("meetingApplyId")int meetingApplyId,
                                               @Query("uploadUserId")int uploadUserId);
}
