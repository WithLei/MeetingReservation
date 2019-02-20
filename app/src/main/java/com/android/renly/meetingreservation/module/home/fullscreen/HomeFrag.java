package com.android.renly.meetingreservation.module.home.fullscreen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.acker.simplezxing.activity.CaptureActivity;
import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.LectureAdapter;
import com.android.renly.meetingreservation.api.bean.Lecture;
import com.android.renly.meetingreservation.listener.ItemClickListener;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.booking.roomList.RoomListActivity;
import com.android.renly.meetingreservation.module.booking.search.SearchActivity;
import com.android.renly.meetingreservation.module.meeting.MeetingActivity;
import com.android.renly.meetingreservation.module.mine.mymeeting.MyMeetingActivity;
import com.android.renly.meetingreservation.utils.LogUtils;
import com.android.renly.meetingreservation.widget.RecycleViewDivider;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class HomeFrag extends BaseFragment implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnCalendarLongClickListener,
        CalendarView.OnYearChangeListener {
    @BindView(R.id.btn01)
    LinearLayout btn01;
    @BindView(R.id.tv_month_day)
    TextView mTvMonthDay;
    @BindView(R.id.tv_year)
    TextView mTvYear;
    @BindView(R.id.tv_lunar)
    TextView mTvLunar;
    @BindView(R.id.tv_current_day)
    TextView mTvCurrentDay;
    @BindView(R.id.rl_tool)
    RelativeLayout mRlTool;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.calendarLayout)
    CalendarLayout mCalendarLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    Unbinder unbinder;

    private int mYear;

    private List<Lecture> lectureList;

    @Override
    protected void initInjector() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.frag_home;
    }

    @Override
    protected void initData(Context content) {

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

        initLectureListData();

        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> mCalendarLayout.shrink(),
                        throwable -> LogUtils.printLog(throwable.getMessage()));

        initView();

    }

    private void initLectureListData() {
        lectureList = new ArrayList<>();
        lectureList.add(new Lecture("http://149.28.149.136:8080/image/speaker01.jpg",
                "个人发展如何借助趋势的力量", "叶修，一个专门研究思维方法与学习策略的人，《深度思维》作者"
                , new Date().getTime(), "5小时"));
        lectureList.add(new Lecture("http://149.28.149.136:8080/image/speaker02.jpg",
                "GDPR来了，你我应当注意什么", "王融，腾讯研究院资深专家。长期从事电信、互联网立法与监管政策研究"
                , new Date().getTime(), "5小时"));
        lectureList.add(new Lecture("http://149.28.149.136:8080/image/speaker01.jpg",
                "如何选到适合自己的好专业", "叶修，一个专门研究思维方法与学习策略的人，《深度思维》作者"
                , new Date().getTime(), "5小时"));
        lectureList.add(new Lecture("http://149.28.149.136:8080/image/speaker02.jpg",
                "GDPR来了，你我应当注意什么", "王融，腾讯研究院资深专家。长期从事电信、互联网立法与监管政策研究"
                , new Date().getTime(), "5小时"));
    }

    protected RecyclerView.LayoutManager mLayoutManager;

    private LectureAdapter adapter;
    private void initAdapter() {
        adapter = new LectureAdapter(getContext(), lectureList);
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
//                jumpToActivity();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        // 调整draw缓存,加速recyclerview加载
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    @Override
    protected void initView() {
        recyclerView.setFocusable(false);
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnCalendarLongClickListener(this, false);
        mTvYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTvMonthDay.setText(mCalendarView.getCurMonth() + "月"
                + mCalendarView.getCurDay() + "日");
        mTvLunar.setText("今日");
        mTvCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));


        initAdapter();
        initRecyclerView();
    }

    @Override
    public void ScrollToTop() {
        scrollView.smoothScrollTo(0,0);
    }

    @OnClick({R.id.btn01, R.id.btn02, R.id.btn03, R.id.btn04, R.id.tv_month_day, R.id.fl_current, R.id.active_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn01:
                startActivityForResult(new Intent(mContent, CaptureActivity.class), CaptureActivity.REQ_CODE);
                break;
            case R.id.btn02:
                jumpToActivity(SearchActivity.class);
                break;
            case R.id.btn03:
                jumpToActivity(MyMeetingActivity.class);
                break;
            case R.id.btn04:
                break;
            case R.id.tv_month_day:
                if (!mCalendarLayout.isExpand()) {
                    mCalendarView.showYearSelectLayout(mYear);
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTvLunar.setVisibility(View.GONE);
                mTvYear.setVisibility(View.GONE);
                mTvMonthDay.setText(String.valueOf(mYear));
                break;
            case R.id.fl_current:
                mCalendarView.scrollToCurrent();
                break;
            case R.id.active_activity:
                jumpToActivity(MeetingActivity.class);
                break;
        }
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

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTvLunar.setVisibility(View.VISIBLE);
        mTvYear.setVisibility(View.VISIBLE);
        mTvMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTvYear.setText(String.valueOf(calendar.getYear()));
        mTvLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }

    @Override
    public void onYearChange(int year) {
        mTvMonthDay.setText(String.valueOf(year));
    }

    @Override
    public void onCalendarLongClickOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarLongClick(Calendar calendar) {
        Log.e("onDateLongClick", "  -- " + calendar.getDay() + "  --  " + calendar.getMonth());
    }
}
