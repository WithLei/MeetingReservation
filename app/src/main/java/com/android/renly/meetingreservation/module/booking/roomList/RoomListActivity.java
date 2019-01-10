package com.android.renly.meetingreservation.module.booking.roomList;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.ConstellationAdapter;
import com.android.renly.meetingreservation.adapter.GirdDropDownAdapter;
import com.android.renly.meetingreservation.adapter.ListDropDownAdapter;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.booking.room.BookingRoomActivity;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomListActivity extends BaseActivity {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"区域", "时段", "排序", "筛选"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter placeAdapter;
    private ListDropDownAdapter timeAdapter;
    private ListDropDownAdapter sortAdapter;
    private ConstellationAdapter constellationAdapter;

    private String places[] = {"不限", "国际创意园-1幢", "国际创意园-3B幢", "电商产业园1号楼", "电商产业园2-1号楼", "电子科技技术大厦", "青年创业社区5号楼", "网络科技园主C楼", "商务产业园C3幢", "商务产业园2幢"};
    private String times[] = {"不限", "今日", "未来三天", "未来一周", "未来一月内", "未来半年内"};
    private String sorts[] = {"不限", "推荐排序", "热度最高", "点赞最高", "价格最低", "价格最高"};
    private String constellations[] = {"不限", "30人以下", "31-50人", "51-100人", "101-200\n人", "200人\n以上 ", "无需审核\n", "场地认证\n", "立即确认", "投影仪", "电子屏", "演讲台", "同声传译\n", "200-400平米", "400-600平米", "600-800平米","800-1000平米","1000平米以上"};

    private int constellationPosition = 0;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_rooomlist;
    }

    @Override
    protected void initData() {

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
        final View constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
        constellationAdapter = new ConstellationAdapter(this, Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);
        TextView ok = ButterKnife.findById(constellationView, R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);
                mDropDownMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(placeView);
        popupViews.add(timeView);
        popupViews.add(sortView);
        popupViews.add(constellationView);

        //add item click event
        placeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                placeAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : places[position]);
                mDropDownMenu.closeMenu();
            }
        });

        timeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                timeAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : times[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sortView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sortAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sorts[position]);
                mDropDownMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        //init context view
//        TextView contentView = new TextView(this);
//        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        contentView.setText("内容显示区域");
//        contentView.setGravity(Gravity.CENTER);
//        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);


        //init dropdownview
        View contentView = View.inflate(this, R.layout.layout_roomlist, null);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        contentView.findViewById(R.id.item).setOnClickListener(view -> jumpToActivity(BookingRoomActivity.class));
    }

//    @OnClick(R.id.item)
//    public void onViewClicked() {
//        jumpToActivity(BookingRoomActivity.class);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
