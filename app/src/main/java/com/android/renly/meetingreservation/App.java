package com.android.renly.meetingreservation;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.renly.meetingreservation.injector.components.ApplicationComponent;
import com.android.renly.meetingreservation.injector.components.DaggerApplicationComponent;
import com.android.renly.meetingreservation.injector.modules.ApplicationModule;
import com.android.renly.meetingreservation.utils.toast.ToastUtils;

public class App extends Application {
    private static Context context;
    private static ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initInjector();
        initConfig();
    }

    private void initConfig() {
        ToastUtils.init(getContext());
    }

    /**
     * 初始化注射器
     */
    private void initInjector() {
//         这里不做注入操作，只提供一些全局单例数据
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getAppComponent() {
        return mAppComponent;
    }

    public static boolean iSLOGIN() {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getBoolean(IS_LOGIN, false) && !TextUtils.isEmpty(String.valueOf(sp.getLong(USER_UID_KEY, 0)));
    }

    public static boolean isRemeberPwdUser(){
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getBoolean(IS_REMEBER_PWD_USER,false);
    }

    public static void setRemeberPwdUser(boolean isRemeber){
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_REMEBER_PWD_USER,isRemeber);
        editor.apply();
    }

    public static String getEmail() {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getString(USER_EMAIL_KEY, "");
    }

    public static void setEmail(String email) {
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_EMAIL_KEY, email);
        editor.apply();
    }

    public static String getPwd(){
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME, MODE_PRIVATE);
        return sp.getString(USER_PWD_KEY,"");
    }

    public static void setPwd(String pwd){
        SharedPreferences sp = context.getSharedPreferences(MY_SP_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_PWD_KEY, pwd);
        editor.apply();
    }

    public static Context getContext() {
        return context;
    }

    public static final String MY_SP_NAME = "MeetingReservation";
    public static final String USER_UID_KEY = "user_uid";
    public static final String IS_LOGIN ="is_login";
    public static final String USER_EMAIL_KEY = "user_email";
    public static final String USER_PWD_KEY = "user_pwd";
    public static final String IS_REMEBER_PWD_USER = "is_remember_pwd_user";


}
