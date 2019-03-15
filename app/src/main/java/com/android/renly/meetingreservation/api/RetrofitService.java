package com.android.renly.meetingreservation.api;

import android.graphics.Bitmap;

import com.android.renly.meetingreservation.App;
import com.android.renly.meetingreservation.api.api.FaceApi;
import com.android.renly.meetingreservation.api.api.MeetingApi;
import com.android.renly.meetingreservation.api.api.MeetingRoomApi;
import com.android.renly.meetingreservation.api.api.ServiceApi;
import com.android.renly.meetingreservation.api.api.TagApi;
import com.android.renly.meetingreservation.api.api.UploadApi;
import com.android.renly.meetingreservation.api.api.UserApi;
import com.android.renly.meetingreservation.api.bean.AskMeeting;
import com.android.renly.meetingreservation.api.bean.AskMeetingRoom;
import com.android.renly.meetingreservation.api.bean.AskService;
import com.android.renly.meetingreservation.api.bean.AskTag;
import com.android.renly.meetingreservation.api.bean.AskUser;
import com.android.renly.meetingreservation.utils.network.NetConfig;
import com.android.renly.meetingreservation.utils.upload.FileUploadObserver;
import com.android.renly.meetingreservation.utils.upload.UploadFileRequestBody;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 整个网络通信服务的启动控制，必须先调用初始化函数才能正常使用网络通信接口
 */
public class RetrofitService {
    private static UserApi userApi;
    private static ServiceApi serviceApi;
    private static MeetingApi meetingApi;
    private static MeetingRoomApi meetingRoomApi;
    private static TagApi tagApi;
    private static UploadApi uploadApi;
    private static FaceApi faceApi;

    private RetrofitService(){
        throw new AssertionError();
    }

    /**
     * 初始化网络通信服务
     */
    public static void init() {
        Cache cache = new Cache(new File(App.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient client = new OkHttpClient().newBuilder().cache(cache)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(NetConfig.BASE_USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        userApi = retrofit.create(UserApi.class);

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(NetConfig.BASE_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        serviceApi = retrofit.create(ServiceApi.class);

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(NetConfig.BASE_MEETING_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        meetingApi = retrofit.create(MeetingApi.class);

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(NetConfig.BASE_MEETINGROOM_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        meetingRoomApi = retrofit.create(MeetingRoomApi.class);

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(NetConfig.BASE_TAG_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        tagApi = retrofit.create(TagApi.class);

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(NetConfig.BASE_UPLOAD)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        uploadApi = retrofit.create(UploadApi.class);

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(NetConfig.GET_FACE_URL + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        faceApi = retrofit.create(FaceApi.class);
    }

    /**************************************             API             **************************************/

    /**
     * 获取人脸
     */
    public static Observable<ResponseBody> getFaceImg() {
        return faceApi.getFaceImg(NetConfig.GET_FACE_URL)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取特征值
     */
    public static Observable<ResponseBody> getEigenValues() {
        return faceApi.getEigenvalues(NetConfig.GET_EIGENVALUES)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取用户对象
     */
    public static Observable<ResponseBody> getUser(int id) {
        return userApi.getUser(new AskUser(id))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 根据手机号查询用户
     */
    public static Observable<ResponseBody> getEntityByPhone(String phone) {
        return userApi.getEntityByPhone(new AskUser(phone))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 用户登录
     */
    public static Observable<ResponseBody> doLogin(String type,String phone, String password) {
        return userApi.doLogin(new AskUser(type,phone,password))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 用户信息修改
     */
    public static Observable<ResponseBody> doUserUpdate(
            int id, String name, String phone, String email, int role, String company, String pwd) {
        return userApi.doUserUpdate(new AskUser(id, name, phone, email, role, company ,pwd))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 用户查询
     */
    public static Observable<ResponseBody> getUserListEntity(int page, int size, String order, String phone) {
        return userApi.getListEntity(new AskUser(page,size,order,phone))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 用户注册
     */
    public static Observable<ResponseBody> doRegister(String name, String phone, String email, String pwd) {
        return userApi.doRegister(new AskUser(name, phone, email, pwd))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 请求服务
     */
    public static Observable<ResponseBody> addService(int workerId, String content) {
        return serviceApi.addService(new AskService(workerId, content))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询参与过的会议
     */
    public static Observable<ResponseBody> getListByAttendWorker(int userId) {
        return meetingApi.getListByAttendWorker(new AskMeeting(userId))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 添加与会人员
     */
    public static Observable<ResponseBody> addAttenderWorker(int id, int userId) {
        return meetingApi.addAttenderWorker(new AskMeeting(id, userId))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 删除与会人员
     */
    public static Observable<ResponseBody> deleteAttenderWorker(int id, int userId) {
        return meetingApi.deleteAttenderWorker(new AskMeeting(id, userId))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询预约单
     */
    public static Observable<ResponseBody> getMeetingListEntity(int userId) {
        return meetingApi.getListEntity(new AskMeeting(userId))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 预约会议室
     */
    public static Observable<ResponseBody> doMeetingApply(int workerId, String topic,
           String intro, String beginTime, String endTime, int attendance, int flexible) {
        return meetingApi.getListEntity(new AskMeeting(workerId, topic, intro,
                beginTime, endTime, attendance, flexible))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 修改会议室信息
     */
    public static Observable<ResponseBody> doMeetingRoomUpdate (AskMeetingRoom askMeetingRoom) {
        return meetingRoomApi.doMeetingRoomUpdate(askMeetingRoom)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询会议室
     */
    public static Observable<ResponseBody> getMeetingRoomListEntity (AskMeetingRoom askMeetingRoom) {
        return meetingRoomApi.getMeetingRoomListEntity(askMeetingRoom)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 根据会议室ID查询标签
     */
    public static Observable<ResponseBody> getTagById (int id) {
        return tagApi.getTag(new AskTag(id))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 上传头像的封装
     */
    public static void uploadAvatar(File file, FileUploadObserver<ResponseBody>fileUploadObserver) {
        UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(file, fileUploadObserver);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), uploadFileRequestBody);
        uploadApi.uploadAvatar(part,26)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileUploadObserver);
    }

    /**
     * 上传文件的封装
     */
    public static void uploadFile(File file, int meetingApplyId, int uploadUserId,
                                                      FileUploadObserver<ResponseBody> fileUploadObserver) {
        UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(file, fileUploadObserver);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), uploadFileRequestBody);
        uploadApi.uploadFileToApply(part, meetingApplyId, uploadUserId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileUploadObserver);
    }
}
