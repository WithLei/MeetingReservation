package com.android.renly.meetingreservation.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

/**
 * Created by yangl on 2017/5/25.
 * 主题相关工具类
 */

public class ThemeUtil {
    public static int getThemeColor(Context context, int attr) {
        //attr R.attr.colorAccent
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{attr});
        int color = a.getColor(0, 0);
        a.recycle();

        return color;
    }
}
