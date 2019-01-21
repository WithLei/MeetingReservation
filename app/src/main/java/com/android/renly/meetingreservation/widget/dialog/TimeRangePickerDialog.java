package com.android.renly.meetingreservation.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.utils.CommonUtils;
import com.android.renly.meetingreservation.utils.LogUtils;

import java.lang.reflect.Field;
import java.util.List;

public class TimeRangePickerDialog extends Dialog {
    private Context context;

    private String startTime;
    private String endTime;

    private int screenWidth;

    private TimePicker timePickerStart;
    private TimePicker timePickerEnd;

    private View cancelBtn, submitBtn;

    private ConfirmAction confirmAction;

    public TimeRangePickerDialog(Context context) {

        super(context);

        this.context = context;
    }

    public TimeRangePickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

        this.context = context;
    }

    public TimeRangePickerDialog(Context context, int theme) {

        super(context, theme);

        this.context = context;
    }

    public TimeRangePickerDialog(Context context, String startAndEndTime, ConfirmAction confirmAction) {
        super(context, R.style.MyDialog);

        this.context = context;

        List<String> strings = CommonUtils.getRegEx(startAndEndTime, "\\d+:\\d+");
        if (!CommonUtils.isNull(strings) && strings.size() >= 2) {
            this.startTime = CommonUtils.getRegEx(startAndEndTime, "\\d+:\\d+").get(0);
            this.endTime = CommonUtils.getRegEx(startAndEndTime, "\\d+:\\d+").get(1);
        }

        this.confirmAction = confirmAction;

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels - getDensityValue(80, context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_time_range_picker, null);
        setContentView(view);
        getWindow().setLayout(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        initView();
        initData();
        setEvent();

    }

    private void initView() {

        timePickerStart = (TimePicker) findViewById(R.id.timePickerStart);
        timePickerEnd = (TimePicker) findViewById(R.id.timePickerEnd);
        cancelBtn = findViewById(R.id.cancelBtn);
        submitBtn = findViewById(R.id.submitBtn);

    }

    private void initData() {

        timePickerStart.setIs24HourView(true);
        timePickerEnd.setIs24HourView(true);
        timePickerStart.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        timePickerEnd.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);

        setTimePickerDividerColor(timePickerStart);
        setTimePickerDividerColor(timePickerEnd);

        if (!CommonUtils.isNull(startTime) && !CommonUtils.isNull(endTime)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePickerStart.setHour(Integer.parseInt(startTime.substring(0, startTime.indexOf(":"))));
                timePickerStart.setMinute(Integer.parseInt(startTime.substring(startTime.indexOf(":") + 1)));

                timePickerEnd.setHour(Integer.parseInt(endTime.substring(0, endTime.indexOf(":"))));
                timePickerEnd.setMinute(Integer.parseInt(endTime.substring(endTime.indexOf(":") + 1)));
            } else {
                timePickerStart.setCurrentHour(Integer.parseInt(startTime.substring(0, startTime.indexOf(":"))));
                timePickerStart.setCurrentMinute(Integer.parseInt(startTime.substring(startTime.indexOf(":") + 1)));

                timePickerEnd.setCurrentHour(Integer.parseInt(endTime.substring(0, endTime.indexOf(":"))));
                timePickerEnd.setCurrentMinute(Integer.parseInt(endTime.substring(endTime.indexOf(":") + 1)));
            }

        }

        timePickerStart.setOnTimeChangedListener((timePicker, hourOfDay, minute) -> {

            String h = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
            String m = minute < 10 ? "0" + minute : "" + minute;
            startTime = h + ":" + m;
        });
        timePickerEnd.setOnTimeChangedListener((timePicker, hourOfDay, minute) -> {
            String h = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
            String m = minute < 10 ? "0" + minute : "" + minute;
            endTime = h + ":" + m;
        });
    }

    private void setEvent() {

        cancelBtn.setOnClickListener(v -> {

            confirmAction.onLeftClick();
            dismiss();
        });

        submitBtn.setOnClickListener(v -> {

            confirmAction.onRightClick(startTime + " - " + endTime);
            dismiss();
        });

        this.setCanceledOnTouchOutside(true);

    }

    private void setTimePickerDividerColor(TimePicker timePicker) {
        LinearLayout llFirst = (LinearLayout) timePicker.getChildAt(0);
        FrameLayout mSpinners = (FrameLayout) llFirst.getChildAt(0);
        LogUtils.printLog("mSpinner.size() == " + mSpinners.getChildCount());
//        LogUtils.printLog("mSpinners2 " + mSpinners.getChildAt(1).getClass());
        for (int i = 0; i < mSpinners.getChildCount(); i++) {
            if (mSpinners.getChildAt(i) instanceof NumberPicker) {
                LogUtils.printLog("i" + i + "is numberpicker");
                Field[] pickerFields = NumberPicker.class.getDeclaredFields();
                setPickerMargin((NumberPicker) mSpinners.getChildAt(i));
                setNumberPickerTextSize((NumberPicker) mSpinners.getChildAt(i));
                for (Field pf : pickerFields) {
                    if (pf.getName().equals("mSelectionDivider")) {
                        pf.setAccessible(true);
                        try {
                            pf.set(mSpinners.getChildAt(i), new ColorDrawable());
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (Resources.NotFoundException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            } else{
                LogUtils.printLog("i" + i + "is not numberpicker is " +  mSpinners.getChildAt(i).getClass());
                LinearLayout ll = (LinearLayout) mSpinners.getChildAt(i);
                LogUtils.printLog("ll size = " + ll.getChildCount());
                for (int t = 0;t < ll.getChildCount();t++) {
                    LogUtils.printLog("t = " + t + " " + ll.getChildAt(t));
                    if (ll.getChildAt(i) instanceof TextView)
                        setTextSize((TextView)ll.getChildAt(t));
                }
            }

        }
    }

    private void setTextSize(TextView tv) {
        LogUtils.printLog("setTextSize");
        tv.setTextSize(12);
    }

    /**
     * 设置picker的字体大小
     */
    private void setNumberPickerTextSize(NumberPicker np) {
        LogUtils.printLog("setNumPicker");
        EditText et = findEditText(np);
        et.setFocusable(false);
        et.setGravity(Gravity.CENTER);
        et.setTextSize(10);
        LogUtils.printLog("设置picker");
    }

    private EditText findEditText(NumberPicker np) {
        if (null != np)
        {
            for (int i = 0; i < np.getChildCount(); i++)
            {
                View child = np.getChildAt(i);

                if (child instanceof EditText)
                {
                    return (EditText)child;
                }
            }
        }

        return null;
    }

    /**
     * 设置picker之间的间距
     */
    private void setPickerMargin(NumberPicker picker) {
        LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) picker.getLayoutParams();
        p.setMargins(-getDensityValue(16, context), 0, -getDensityValue(16, context), 0);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            p.setMarginStart(-getDensityValue(16, context));
            p.setMarginEnd(-getDensityValue(16, context));
        }
    }


    public interface ConfirmAction {

        void onLeftClick();

        void onRightClick(String startAndEndTime);
    }

    public static int getDensityValue(float value, Context activity) {

        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();

        return (int) Math.ceil(value * displayMetrics.density);
    }
}
