package com.android.renly.meetingreservation.module.meeting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.renly.meetingreservation.App;
import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.folder.upload.UploadActivity;
import com.android.renly.meetingreservation.module.map.MapActivity;
import com.android.renly.meetingreservation.module.meeting.addPeople.PeopleListActivity;
import com.android.renly.meetingreservation.module.meeting.edit.EditMeetingActivity;
import com.android.renly.meetingreservation.utils.IntentUtils;
import com.android.renly.meetingreservation.utils.LogUtils;
import com.android.renly.meetingreservation.utils.network.NetConfig;
import com.android.renly.meetingreservation.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingActivity extends BaseActivity {
    @BindView(R.id.bigImg)
    ImageView img;
    @BindView(R.id.share)
    RelativeLayout share;
    @BindView(R.id.meeting_title)
    TextView title;
    @BindView(R.id.upload)
    TextView upload;
    @BindView(R.id.avatar1)
    CircleImageView avatar1;
    @BindView(R.id.avatar2)
    CircleImageView avatar2;
    @BindView(R.id.avatar3)
    CircleImageView avatar3;
    @BindView(R.id.avatar4)
    CircleImageView avatar4;
    @BindView(R.id.avatar5)
    CircleImageView avatar5;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_avatar)
    CircleImageView userAvatar;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.intro)
    TextView intro;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.edit)
    LinearLayout editLayout;

    private boolean isEmpty = false;

    // Simple Adapter param
    private String[] from = new String[] {
            "name","size"
    };
    private int[] to = new int[] {
            R.id.folder_name, R.id.folder_size
    };

    @Override
    protected int getLayoutID() {
        return R.layout.activity_meeting;
    }

    @Override
    protected void initData() {
        isEmpty = getIntent().getBooleanExtra("doEdit", false);
    }

    @Override
    protected void initView() {
        initToolBar(true, "会议信息");
        initSlidr();
        if (isEmpty) //如果不是从编辑会议进入
            initNormalData();
        else
            initEmptyData();
    }

    private void initEmptyData() {
        title.setText("未命名会议");
        userName.setText(App.getUserName());
        Picasso.get()
                .load("http://149.28.149.136:8080/image/user1.jpg")
                .into(userAvatar);
        tvLocation.setText("18B楼212 高级会议室4");
        people.setText("（1）");
        Picasso.get()
                .load("http://149.28.149.136:8080/image/user1.jpg")
                .into(avatar1);
        intro.setText("会议简介信息空空如也~");
        editLayout.setVisibility(View.VISIBLE);
    }

    private void initNormalData() {
        title.setText("关于新产品的售价");
        userName.setText("市场部");
        tvLocation.setText("国际创意园-1楼");
        people.setText("（9）");
        Picasso.get()
                .load("http://149.28.149.136:8080/image/room01.jpg")
                .into(img);
        CircleImageView[] avatarList = {avatar1,avatar2,avatar3,avatar4,avatar5};
        for (int i = 1;i <= 5;i++) {
            Picasso.get()
                    .load("http://149.28.149.136:8080/image/user" + i + ".jpg")
                    .into(avatarList[i-1]);
            avatarList[i-1].setVisibility(View.VISIBLE);
        }
        intro.setText(getResources().getString(R.string.meeting_intro));
        List<Map<String, String>>list = new ArrayList<>();
        list.add(getFolderMap("关于新产品售价.pdf", "1.79MB"));
        list.add(getFolderMap("露露饿狼号地块.word", "1.64MB"));
        list.add(getFolderMap("回不来科技离开.pdf", "78MB"));
        list.add(getFolderMap("等各环节.pdf", "3.4MB"));
        list.add(getFolderMap("的个是个梵蒂冈.pdf", "12.45MB"));
        list.add(getFolderMap("是固定个.pdf", "12MB"));
        list.add(getFolderMap("东莞市大反攻昆.pdf", "25.44MB"));
        LogUtils.printLog("list.size() = " + list.size());
        listView.setAdapter(new SimpleAdapter(this,list,R.layout.item_folder_s, from, to));
        setListViewHeightBasedOnChildren(listView);
        editLayout.setVisibility(View.GONE);
    }

    private static void setListViewHeightBasedOnChildren(ListView listView) {
        try{
            // 获取ListView对应的Adapter
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                return;
            }

            int totalHeight = 0;
            for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
                // listAdapter.getCount()返回数据项的数目
                View listItem = listAdapter.getView(i, null, listView);
                // 计算子项View 的宽高
                listItem.measure(0, 0);
                // 统计所有子项的总高度
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            // listView.getDividerHeight()获取子项间分隔符占用的高度
            // params.height最后得到整个ListView完整显示需要的高度
            listView.setLayoutParams(params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Map<String, String> getFolderMap(String name, String size) {
        Map<String,String>map = new HashMap<>();
        map.put("name", name);
        map.put("size", size);
        return map;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case EditMeetingActivity.requestCode:
                    title.setText(data.getStringExtra("title"));
                    intro.setText(data.getStringExtra("intro"));
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideKeyBoard();
    }

    @OnClick({R.id.edit, R.id.ll_people, R.id.location, R.id.share, R.id.upload})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.edit:
                intent = new Intent(this, EditMeetingActivity.class);
                intent.putExtra("title", title.getText());
                intent.putExtra("intro", intro.getText());
                startActivityForResult(intent, EditMeetingActivity.requestCode);
                overridePendingTransition(R.anim.bottomin, R.anim.bottomout);
                break;
            case R.id.ll_people:
                jumpToActivity(PeopleListActivity.class);
                break;
            case R.id.location:
                jumpToActivity(MapActivity.class);
                break;
            case R.id.share:
                String data = "邀请你加入这场会议：" + title.getText().toString().trim() + "\n" + NetConfig.BASE_MEETING_URL;
                IntentUtils.shareApp(this, data);
                break;
            case R.id.upload:
                intent = new Intent(this, UploadActivity.class);
                startActivityForResult(intent, UploadActivity.requestCode);
                overridePendingTransition(R.anim.translate_in, R.anim.translate_out);
                break;
        }
    }
}
