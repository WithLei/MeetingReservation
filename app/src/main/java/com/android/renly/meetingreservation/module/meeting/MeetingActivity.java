package com.android.renly.meetingreservation.module.meeting;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.acker.simplezxing.activity.CaptureActivity;
import com.android.renly.meetingreservation.App;
import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.folder.upload.UploadActivity;
import com.android.renly.meetingreservation.module.map.MapActivity;
import com.android.renly.meetingreservation.module.meeting.peopleList.PeopleListActivity;
import com.android.renly.meetingreservation.module.meeting.edit.EditMeetingActivity;
import com.android.renly.meetingreservation.utils.IntentUtils;
import com.android.renly.meetingreservation.utils.LogUtils;
import com.android.renly.meetingreservation.utils.network.NetConfig;
import com.android.renly.meetingreservation.utils.toast.ToastUtils;
import com.android.renly.meetingreservation.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MeetingActivity extends BaseActivity
        implements View.OnClickListener {
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
    @BindView(R.id.sign)
    RelativeLayout signLayout;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.iv_sign)
    ImageView ivSign;

    private boolean isAnnouncer = false;

    // Simple Adapter param
    private String[] from = new String[]{
            "name", "size"
    };
    private int[] to = new int[]{
            R.id.folder_name, R.id.folder_size
    };

    private AlertDialog dialog;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_meeting;
    }

    @Override
    protected void initData() {
        isAnnouncer = getIntent().getBooleanExtra("isAnnouncer", false);
    }

    @Override
    protected void initView() {
        initToolBar(true, "会议信息");
        initSlidr();
        if (isAnnouncer) //发布人会议
            initEmptyData();
        else
            initNormalData();
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
        tvSign.setText("发布签到");
        ivSign.setImageResource(R.drawable.ic_push);
    }

    private void initNormalData() {
        title.setText("关于新产品的售价");
        userName.setText("张小龙");
        tvLocation.setText("18B楼212 高级会议室4");
        people.setText("（9）");
        Picasso.get()
                .load("http://149.28.149.136:8080/image/room01.jpg")
                .into(img);
        CircleImageView[] avatarList = {avatar1, avatar2, avatar3, avatar4, avatar5};
        for (int i = 1; i <= 5; i++) {
            Picasso.get()
                    .load("http://149.28.149.136:8080/image/user" + i + ".jpg")
                    .into(avatarList[i - 1]);
            avatarList[i - 1].setVisibility(View.VISIBLE);
        }
        intro.setText(getResources().getString(R.string.meeting_intro));
        List<Map<String, String>> list = new ArrayList<>();
        list.add(getFolderMap("产品样例.pdf", "17.28MB"));
        list.add(getFolderMap("产品A实体图.png", "3.45MB"));
        list.add(getFolderMap("产品A渲染图.pdf", "7.18MB"));
        list.add(getFolderMap("关于新产品的售价.word", "14.68MB"));
        list.add(getFolderMap("新产品介绍.ppt", "12.89MB"));
        list.add(getFolderMap("新产品介绍.mp4", "25.32MB"));
        listView.setAdapter(new SimpleAdapter(this, list, R.layout.item_folder_s, from, to));
        setListViewHeightBasedOnChildren(listView);
        editLayout.setVisibility(View.GONE);
        tvSign.setText("二维码签到");
        ivSign.setImageResource(R.drawable.ic_sign);
    }

    private static void setListViewHeightBasedOnChildren(ListView listView) {
        try {
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
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            // listView.getDividerHeight()获取子项间分隔符占用的高度
            // params.height最后得到整个ListView完整显示需要的高度
            listView.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getFolderMap(String name, String size) {
        Map<String, String> map = new HashMap<>();
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
                case CaptureActivity.REQ_CODE:
                    String result = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);
                    ToastUtils.ToastLong("签到完成！");
                    ivSign.setImageResource(R.drawable.ic_check_24dp);
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideKeyBoard();
    }

    @OnClick({R.id.edit, R.id.ll_people, R.id.location, R.id.share, R.id.upload, R.id.sign})
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
                if (isAnnouncer) {
                    intent = new Intent(this, PeopleListActivity.class);
                    intent.putExtra("isAnnouncer", true);
                    startActivity(intent);
                } else
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
            case R.id.sign:
                View mView;
                if (isAnnouncer) { //发布者界面
                    mView = View.inflate(this, R.layout.layout_pushsign, null);
                    mView.findViewById(R.id.face).setOnClickListener(this);
                    mView.findViewById(R.id.qr).setOnClickListener(this);
                    mView.findViewById(R.id.num).setOnClickListener(this);
                    dialog = new AlertDialog.Builder(this)
                            .setView(mView)
                            .setCancelable(true)
                            .create();
                    dialog.show();
                } else { //与会人界面
                    // 人脸签到
//                    jumpToActivityBottom(FaceRecognizeActivity.class);
                    // 二维码签到
                    startActivityForResult(new Intent(this, CaptureActivity.class),
                            CaptureActivity.REQ_CODE);

                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        String result = "";
        switch (view.getId()) {
            case R.id.face:
                result = "人脸签到";
                break;
            case R.id.qr:
                result = "二维码签到";
                break;
            case R.id.num:
                result = "随机数签到";
                break;
        }
        tvSign.setText(result);
        ToastUtils.ToastLong("已为本场会议添加" + result + "，会前15分钟内有效。");
        dialog.cancel();
        dialog = null;
        if (result.equals("二维码签到")) { //弹出二维码
            new AlertDialog.Builder(this)
                    .setView(View.inflate(this, R.layout.dialog_qr, null))
                    .setCancelable(true)
                    .create()
                    .show();
        }

    }

}
