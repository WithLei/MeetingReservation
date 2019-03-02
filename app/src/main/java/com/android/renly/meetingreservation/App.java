package com.android.renly.meetingreservation;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.android.renly.meetingreservation.api.RetrofitService;
import com.android.renly.meetingreservation.injector.components.ApplicationComponent;
import com.android.renly.meetingreservation.injector.components.DaggerApplicationComponent;
import com.android.renly.meetingreservation.injector.modules.ApplicationModule;
import com.android.renly.meetingreservation.utils.LogUtils;
import com.android.renly.meetingreservation.utils.sHA1Utils;
import com.android.renly.meetingreservation.utils.toast.ToastUtils;
import com.squareup.picasso.Picasso;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import me.leolin.shortcutbadger.ShortcutBadger;

public class App extends MultiDexApplication {
    private static Context context;
    private static ApplicationComponent mAppComponent;


    private UmengMessageHandler messageHandler = new UmengMessageHandler() {
        @Override
        public int getNotificationDefaults(Context context, UMessage uMessage) {
            int badgeCount = getBadgeCount() +1;
            setBadgeCount(badgeCount);
            ShortcutBadger.applyCount(context, badgeCount);
            return super.getNotificationDefaults(context, uMessage);
        }
    };

    private UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

        @Override
        public void dealWithCustomAction(Context context, UMessage msg) {
            LogUtils.printLog("dealWithCustomAction");
            clearBadge();
            super.dealWithCustomAction(context,msg);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initInjector();
        initConfig();
        initPush();
//        LogUtils.printLog("sHA1 = " + sHA1Utils.sHA1(this));
    }

    private void initPush() {
        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
        // 参数一：当前上下文context；
        // 参数二：应用申请的Appkey（需替换）；
        // 参数三：渠道名称；
        // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
        // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
        UMConfigure.init(this,
                "5c6cdc20b465f5612f00017a",
                "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "ed1f9bf9c4bace623c05684fbd698c52");

        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                LogUtils.printLog("注册成功：deviceToken：-------->  " + deviceToken);
            }
            @Override
            public void onFailure(String s, String s1) {
                LogUtils.printLog("注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });

        ShortcutBadger.applyCount(this, getBadgeCount());

        /**
         * 每当有通知送达时，均会回调getNotification方法，
         * 因此可以通过监听此方法来判断通知是否送达。
         */
        mPushAgent.setMessageHandler(messageHandler);
        /**
         * 自定义用户点击通知栏时的后续动作。**自定义行为**的数据放在UMessage.custom字段
         */
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }

    private void initConfig() {
        ToastUtils.init(getContext());
        RetrofitService.init();
    }

    /**
     * 初始化注射器
     */
    private void initInjector() {
    //这里不做注入操作，只提供一些全局单例数据
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * HOME键退出应用程序
     * 程序在内存清理的时候执行
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    private void clearBadge() {
        int badgeCount = 0;
        setBadgeCount(badgeCount);
        ShortcutBadger.applyCount(context, badgeCount);
    }

    public static ApplicationComponent getAppComponent() {
        return mAppComponent;
    }

    public static boolean iSLOGIN() {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getBoolean(IS_LOGIN, false) && !TextUtils.isEmpty(String.valueOf(sp.getLong(USER_UID_KEY, 0)));
    }

    public static boolean isRemeberPwdUser() {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getBoolean(IS_REMEBER_PWD_USER, false);
    }

    public static void setRemeberPwdUser(boolean isRemeber) {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_REMEBER_PWD_USER, isRemeber);
        editor.apply();
    }

    public static String getUserPhone() {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getString(USER_PHONE_KEY, "");
    }

    public static void setUserPhone(String phone) {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_PHONE_KEY, phone);
        editor.apply();
    }

    public static String getUserName() {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getString(USER_NAME_KEY, "");
    }

    public static void setUserName(String name) {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_NAME_KEY, name);
        editor.apply();
    }

    public static String getUserEmail() {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getString(USER_EMAIL_KEY, "");
    }

    public static void setUserEmail(String email) {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_EMAIL_KEY, email);
        editor.apply();
    }

    public static String getPwd() {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getString(USER_PWD_KEY, "");
    }

    public static void setPwd(String pwd) {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_PWD_KEY, pwd);
        editor.apply();
    }

    public static void setBadgeCount(int badgeCount) {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(BADGE_COUNT, badgeCount);
        editor.apply();
    }

    public static int getBadgeCount() {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getInt(BADGE_COUNT, 0);
    }

    public static void setIsLogout(){
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_LOGIN, false);
        editor.remove(USER_UID_KEY);
        editor.remove(USER_NAME_KEY);
        editor.remove(USER_EMAIL_KEY);
        editor.apply();
    }

    public static Context getContext() {
        return context;
    }

    public static final String MY_SP_NAME = "MeetingReservation";
    public static final String USER_UID_KEY = "user_uid";
    public static final String IS_LOGIN = "is_login";
    public static final String USER_PHONE_KEY = "user_phone";
    public static final String USER_PWD_KEY = "user_pwd";
    public static final String USER_NAME_KEY = "user_name";
    public static final String USER_EMAIL_KEY = "user_email";
    public static final String IS_REMEBER_PWD_USER = "is_remember_pwd_user";
    public static final String BADGE_COUNT = "badgeCount";
}
