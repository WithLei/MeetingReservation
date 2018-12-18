package com.android.renly.meetingreservation;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.renly.meetingreservation.injector.components.ApplicationComponent;
import com.android.renly.meetingreservation.injector.components.DaggerApplicationComponent;
import com.android.renly.meetingreservation.injector.modules.ApplicationModule;

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

    public static Context getContext() {
        return context;
    }

    public static final String MY_SP_NAME = "MeetingReservation";
    public static final String USER_UID_KEY = "user_uid";
    public static final String IS_LOGIN ="is_login";

}
