package com.android.renly.meetingreservation.utils;

import android.util.Log;

public class LogUtils {
    /**
     * Log输出
     * error
     * Filter:print
     * @param str
     */
    public static void printLog(String str){
        Log.e("print",str);
    }

    /**
     * ERROR输出
     */
    public static void printError(String err) {
        Log.e("_error",err);
    }
}
