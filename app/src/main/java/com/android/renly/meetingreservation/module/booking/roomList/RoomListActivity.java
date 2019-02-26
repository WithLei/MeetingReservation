package com.android.renly.meetingreservation.module.booking.roomList;

import android.app.DatePickerDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.ConstellationAdapter;
import com.android.renly.meetingreservation.adapter.GirdDropDownAdapter;
import com.android.renly.meetingreservation.adapter.ListDropDownAdapter;
import com.android.renly.meetingreservation.adapter.RoomAdapter;
import com.android.renly.meetingreservation.api.bean.Room;
import com.android.renly.meetingreservation.listener.ItemClickListener;
import com.android.renly.meetingreservation.listener.MyOnDateSetListener;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.booking.room.BookingRoomActivity;
import com.android.renly.meetingreservation.module.map.MapActivity;
import com.android.renly.meetingreservation.widget.RecycleViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomListActivity extends BaseActivity {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    @BindView(R.id.search)
    LinearLayout search;
    @BindView(R.id.search_hint)
    TextView search_hint;
    RecyclerView recyclerView;
    SmartRefreshLayout refresh;

    private List<Room> roomList;

    private String headers[] = {"区域", "时段", "排序", "筛选"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter placeAdapter;
    private ListDropDownAdapter timeAdapter;
    private ListDropDownAdapter sortAdapter;
    private ConstellationAdapter constellationAdapter;

    private String places[] = {"不限", "国际创意园-1幢", "国际创意园-3B幢", "电商产业园1号楼", "电商产业园2-1号楼", "电子科技技术大厦", "青年创业社区5号楼", "网络科技园主C楼", "商务产业园C3幢", "商务产业园2幢"};
    private String times[] = {"不限", "今日", "未来三天", "未来一周", "未来一月内", "未来半年内"};
    private String sorts[] = {"不限", "推荐排序", "热度最高", "点赞最高", "价格最低", "价格最高"};
    private String constellations[] = {"不限", "30人以下", "31-50人", "51-100人", "101-200\n人", "200人\n以上 ", "无需审核\n", "场地认证\n", "立即确认", "投影仪", "电子屏", "演讲台", "同声传译\n", "200-400平米", "400-600平米", "600-800平米", "800-1000平米", "1000平米以上"};

    private String keyWords[] = {"研讨会", "商务", "推介会"};

    private int constellationPosition = 0;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_rooomlist;
    }

    @Override
    protected void initData() {
        roomList = new ArrayList<>();
        roomList.add(new Room("B2楼4037", "多功能会议室", 4.5f, 4, new Date().getTime(),
                true, keyWords, "http://149.28.149.136:8080/image/room01.jpg"));
        roomList.add(new Room("A3楼1003", "大型会议室", 3.5f, 3, new Date().getTime() - 3000,
                false, keyWords, "http://149.28.149.136:8080/image/room02.jpg"));
        roomList.add(new Room("B2楼4037", "多功能会议室", 4.5f, 5, new Date().getTime() - 2000,
                true, keyWords, "http://149.28.149.136:8080/image/room03.jpg"));
        roomList.add(new Room("B2楼4037", "多功能会议室", 4.5f, 4, new Date().getTime(),
                true, keyWords, "http://149.28.149.136:8080/image/room04.jpg"));


    }

    @Override
    protected void initView() {
        initSlidr();
        findViewById(R.id.back).setOnClickListener(view -> finishActivity());

        //init place menu
        final ListView placeView = new ListView(this);
        placeAdapter = new GirdDropDownAdapter(this, Arrays.asList(places));
        placeView.setDividerHeight(0);
        placeView.setAdapter(placeAdapter);

        //init time menu
        final ListView timeView = new ListView(this);
        timeView.setDividerHeight(0);
        timeAdapter = new ListDropDownAdapter(this, Arrays.asList(times));
        timeView.setAdapter(timeAdapter);

        //init sort menu
        final ListView sortView = new ListView(this);
        sortView.setDividerHeight(0);
        sortAdapter = new ListDropDownAdapter(this, Arrays.asList(sorts));
        sortView.setAdapter(sortAdapter);

        //init constellation
        final View constellationView = getLayoutInflater().inflate(R.layout.layout_custom, null);
        GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
        constellationAdapter = new ConstellationAdapter(this, Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);
        TextView ok = ButterKnife.findById(constellationView, R.id.ok);
        ok.setOnClickListener(v -> {
            mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);
            mDropDownMenu.closeMenu();
        });

        //init popupViews
        popupViews.add(placeView);
        popupViews.add(timeView);
        popupViews.add(sortView);
        popupViews.add(constellationView);

        //add item click event
        placeView.setOnItemClickListener((parent, view, position, id) -> {
            placeAdapter.setCheckItem(position);
            mDropDownMenu.setTabText(position == 0 ? headers[0] : places[position]);
            mDropDownMenu.closeMenu();
        });

        timeView.setOnItemClickListener((parent, view, position, id) -> {
            timeAdapter.setCheckItem(position);
            mDropDownMenu.setTabText(position == 0 ? headers[1] : times[position]);
            mDropDownMenu.closeMenu();
        });

        sortView.setOnItemClickListener((parent, view, position, id) -> {
            sortAdapter.setCheckItem(position);
            mDropDownMenu.setTabText(position == 0 ? headers[2] : sorts[position]);
            mDropDownMenu.closeMenu();
        });

        constellation.setOnItemClickListener((parent, view, position, id) -> {
            constellationAdapter.setCheckItem(position);
            constellationPosition = position;
        });

        //init dropdownview
        View contentView = View.inflate(this, R.layout.layout_roomlist, null);
        recyclerView = contentView.findViewById(R.id.recyclerView);
        refresh = contentView.findViewById(R.id.refresh);
        contentView.findViewById(R.id.empty_layout).setVisibility(View.GONE);
        refresh.setVisibility(View.VISIBLE);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        initAdapter();
        initRecyclerView();
    }


    private RoomAdapter adapter;

    private void initAdapter() {
        adapter = new RoomAdapter(this, roomList);
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                jumpToActivity(BookingRoomActivity.class);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    protected RecyclerView.LayoutManager mLayoutManager;

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 20, getResources().getColor(R.color.colorDivider)));
        // 调整draw缓存,加速recyclerview加载
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    @OnClick({R.id.search, R.id.location, R.id.date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                finishActivity();
                break;
            case R.id.location:
                jumpToActivity(MapActivity.class);
                break;
            case R.id.date:
                Calendar calendar = Calendar.getInstance();
                MyOnDateSetListener myOnDateSetListener = new MyOnDateSetListener() {
                    @Override
                    public void afterSetDate(String days) {
                        search_hint.setText(days);
                    }
                };
                new DatePickerDialog(this,
                        myOnDateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }
}
