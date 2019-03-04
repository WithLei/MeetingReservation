package com.android.renly.meetingreservation.module.booking.roomArrangement;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.booking.bookingroom.BookingRoomActivity;
import com.android.renly.meetingreservation.utils.toast.ToastUtils;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomArrangementActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener,
        CalendarView.OnCalendarInterceptListener{
    @BindView(R.id.tv_month_day)
    TextView mTextMonthDay;
    @BindView(R.id.tv_year)
    TextView mTextYear;
    @BindView(R.id.tv_lunar)
    TextView mTextLunar;
    @BindView(R.id.tv_current_day)
    TextView mTextCurrentDay;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.calendarLayout)
    CalendarLayout mCalendarLayout;
    @BindView(R.id.empty_layout)
    ExpandableLayout emptyLayout;
    @BindView(R.id.active_activity)
    ExpandableLayout active1;
    @BindView(R.id.active_activity2)
    ExpandableLayout active2;
    @BindView(R.id.booking)
    Button booking;

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_roomarrangement;
    }

    @Override
    protected void initData() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "假").toString(),
                getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "事").toString(),
                getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议").toString(),
                getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));
        map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d, "记").toString(),
                getSchemeCalendar(year, month, 13, 0xFFedc56d, "记"));
        map.put(getSchemeCalendar(year, month, 14, 0xFFedc56d, "记").toString(),
                getSchemeCalendar(year, month, 14, 0xFFedc56d, "记"));
        map.put(getSchemeCalendar(year, month, 15, 0xFFaacc44, "假").toString(),
                getSchemeCalendar(year, month, 15, 0xFFaacc44, "假"));
        map.put(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记").toString(),
                getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记"));
        map.put(getSchemeCalendar(year, month, 25, 0xFF13acf0, "假").toString(),
                getSchemeCalendar(year, month, 25, 0xFF13acf0, "假"));
        map.put(getSchemeCalendar(year, month, 27, 0xFF13acf0, "多").toString(),
                getSchemeCalendar(year, month, 27, 0xFF13acf0, "多"));
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);
    }

    @Override
    protected void initView() {
        initSlidr();
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mYear == 0) {
                    mYear = mCalendarView.getCurYear();
                }
                if (!mCalendarLayout.isExpand()) {
                    mCalendarView.showYearSelectLayout(mYear);
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);

        //设置日期拦截事件，仅适用单选模式，当前有效
        mCalendarView.setOnCalendarInterceptListener(this);

        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    /**
     * 屏蔽某些不可点击的日期，可根据自己的业务自行修改
     * 如 calendar > 2018年1月1日 && calendar <= 2020年12月31日
     *
     * @param calendar calendar
     * @return 是否屏蔽某些不可点击的日期，MonthView和WeekView有类似的API可调用
     */
    @Override
    public boolean onCalendarIntercept(Calendar calendar) {
        //Log.e("onCalendarIntercept", calendar.toString());
        int day = calendar.getDay();
        return day == 1 || day == 3 || day == 6 || day == 11 ||
                day == 12 || day == 15 || day == 20 || day == 26 ||
                day == mCalendarView.getCurDay();
    }

    @Override
    public void onCalendarInterceptClick(Calendar calendar, boolean isClick) {
        ToastUtils.ToastShort(isClick ? "该日预约已满" : "拦截滚动到无效日期");
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mYear = calendar.getYear();
        mMonth = calendar.getMonth();
        mDay = calendar.getDay();
        mTextMonthDay.setText(mMonth + "月" + mDay + "日");
        mTextYear.setText(String.valueOf(mYear));
        mTextLunar.setText(calendar.getLunar());
        if (!calendar.isAvailable()) {
            mTextLunar.setText("");
            mTextYear.setText("");
            mTextMonthDay.setText("无效日期");
            active1.setVisibility(View.GONE);
            active2.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.VISIBLE);
            booking.setEnabled(false);
        } else {
            booking.setEnabled(true);
            switch (calendar.getDay() % 4) {
                case 0:
                    emptyLayout.collapse();
                    active1.expand();
                    active2.collapse();
                    break;
                case 1:
                    emptyLayout.collapse();
                    active1.collapse();
                    active2.expand();
                    break;
                case 2:
                    emptyLayout.collapse();
                    active1.expand();
                    active2.expand();
                    break;
                case 3:
                    active1.collapse();
                    active2.collapse();
                    emptyLayout.expand();
                    break;
            }
        }
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

    @OnClick({R.id.booking})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.booking:
                String date = mYear + "/" + mMonth + "/" + mDay;
                Intent intent = new Intent(this, BookingRoomActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
                overridePendingTransition(R.anim.bottomin, R.anim.bottomout);
                finishActivityBottom();
                break;
        }
    }
}
