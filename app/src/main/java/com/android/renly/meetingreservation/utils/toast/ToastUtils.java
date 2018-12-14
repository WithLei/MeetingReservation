package com.android.renly.meetingreservation.utils.toast;

import android.content.Context;
import android.widget.Toast;

import static com.android.renly.meetingreservation.utils.LogUtils.printLog;


public class ToastUtils {
    private static Context mContext;
    public static void init(Context context) {
        mContext = context;
    }

    public static void ToastLong(String msg){
        MyToast.showText(mContext,msg, Toast.LENGTH_LONG);
    }

    public static void ToastShort(String msg){
        MyToast.showText(mContext,msg,Toast.LENGTH_SHORT);
    }

    public static void ToastNetWorkError(){
        ToastShort("网络出状况咯ヽ(#`Д´)ﾉ");
    }

    public static void ToastNetWorkError(Exception e) {
        ToastShort("网络出状况咯ヽ(#`Д´)ﾉ");
        printLog("ToastNetWorkError: " + e.getMessage());
    }

    public static void ToastProgramError(){
        ToastShort("程序猿还在努力开发中 ♪(´∇`*)");
    }
}
