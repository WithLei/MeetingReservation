package com.android.renly.meetingreservation.module.mine.mycollection;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.CollectionAdapter;
import com.android.renly.meetingreservation.api.bean.Room;
import com.android.renly.meetingreservation.listener.ItemClickListener;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.booking.room.BookingRoomActivity;
import com.android.renly.meetingreservation.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyCollectionActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CollectionAdapter adapter;
    private List<Room>roomList;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_meetinglist;
    }

    @Override
    protected void initData() {
        initListData();
    }

    private void initListData() {
        roomList = new ArrayList<>();
        roomList.add(new Room("B2楼4037", "多功能会议室", 4, "http://149.28.149.136:8080/image/room01.jpg", "336平米", 255));
        roomList.add(new Room("A3楼1003", "大型会议室", 3, "http://149.28.149.136:8080/image/room02.jpg","500平米", 375));
        roomList.add(new Room("B2楼4037", "多功能会议室", 4, "http://149.28.149.136:8080/image/room03.jpg", "336平米", 255));
        roomList.add(new Room("A3楼1003", "大型会议室", 3, "http://149.28.149.136:8080/image/room04.jpg","500平米", 375));
    }

    @Override
    protected void initView() {
        initToolBar(true, "我的收藏");
        initSlidr();
        initRecyclerView();
        initAdapter();
    }

    /**
     * 初始化recylerView的一些属性
     */
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

    private void initAdapter() {
        adapter = new CollectionAdapter(this, roomList);
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                jumpToActivity(BookingRoomActivity.class);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
