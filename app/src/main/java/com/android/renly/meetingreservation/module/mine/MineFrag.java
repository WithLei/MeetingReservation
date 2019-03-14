package com.android.renly.meetingreservation.module.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.renly.meetingreservation.App;
import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.RetrofitService;
import com.android.renly.meetingreservation.local.faceserver.FaceServer;
import com.android.renly.meetingreservation.injector.components.DaggerMineFragComponent;
import com.android.renly.meetingreservation.injector.modules.MineFragModule;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.home.HomeActivity;
import com.android.renly.meetingreservation.module.mine.joinmeeting.JoinMeetingActivity;
import com.android.renly.meetingreservation.module.mine.mydemand.MyDemandActivity;
import com.android.renly.meetingreservation.module.mine.mycollection.MyCollectionActivity;
import com.android.renly.meetingreservation.module.mine.myrecentlyview.MyRecentlyViewActivity;
import com.android.renly.meetingreservation.module.mine.mymeeting.MyMeetingActivity;
import com.android.renly.meetingreservation.module.user.login.LoginActivity;
import com.android.renly.meetingreservation.utils.LogUtils;
import com.android.renly.meetingreservation.utils.toast.MyToast;
import com.android.renly.meetingreservation.utils.toast.ToastUtils;
import com.android.renly.meetingreservation.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFrag extends BaseFragment
        implements AdapterView.OnItemClickListener, MineFragView {
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.header)
    RelativeLayout header;
    @BindView(R.id.btn01)
    LinearLayout btn01;
    @BindView(R.id.btn02)
    LinearLayout btn02;
    @BindView(R.id.btn03)
    LinearLayout btn03;
    @BindView(R.id.btn04)
    LinearLayout btn04;
    @BindView(R.id.lv_mine_function_list)
    ListView lvMineFunctionList;
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.tabLayout)
    LinearLayout tabLayout;

    @Inject
    protected MineFragPresenter mPresenter;

    @Override
    protected void initInjector() {
        DaggerMineFragComponent.builder()
                .applicationComponent(App.getAppComponent())
                .mineFragModule(new MineFragModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutid() {
        return R.layout.frag_mine;
    }

    @Override
    protected void initData(Context content) {
        FaceServer.getInstance().init(content);

        mPresenter.getData(false);
        lvMineFunctionList.setAdapter(new SimpleAdapter(mContent, mPresenter.getMenuList(),
                R.layout.item_function, new String[]{"icon", "title"},
                new int[]{R.id.icon, R.id.title}));
        lvMineFunctionList.setOnItemClickListener(this);
    }

    @Override
    protected void initView() {
        doRefresh();
    }

    @Override
    public void ScrollToTop() {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch (position) {
            case 0:
                // 设置
                ToastUtils.ToastShort("获取特征值");
                RetrofitService.getEigenValues()
                        .subscribe(responseBody -> {
                            JSONObject obj = null;
                            try {
                                obj = JSON.parseObject(responseBody.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            int statusCode = obj.getInteger("code");
                            String result = obj.getString("msg");

                            if (statusCode == 0 && result.equals("成功")) {
                                doRegisterFace(obj.getJSONArray("data"));
                            }
                        }, throwable -> LogUtils.printError("获取特征值失败" + throwable));
                break;
            case 1:
                clearFaces(null);
                break;
            case 2:
                if (App.iSLOGIN()) {
                    App.setIsLogout();
                    MyToast.showText(App.getContext(),"退出登录成功", Toast.LENGTH_SHORT,true);
                    mActivity.doRefresh();
                }
                else
                    ToastUtils.ToastShort("关于本程序");
                break;
        }
    }

    private void doRegisterFace(JSONArray data) {
        LogUtils.printLog("data=" + data.toJSONString());
        for (int i = 0;i < data.size();i++) {
            JSONObject jsonObject = data.getJSONObject(i);
            boolean flag = FaceServer.getInstance()
                    .doMyRegister(jsonObject.getBytes("face"),jsonObject.getString("name"));
            if (flag) {
                LogUtils.printLog(i+1 + "/" + data.size() + " " + jsonObject.getString("name") + " 获取成功");
            } else{
                LogUtils.printLog(i+1 + "/" + data.size() + " " + jsonObject.getString("name") + " 获取失败");
            }
        }
    }

    public void clearFaces(View view) {
        int faceNum = FaceServer.getInstance().getFaceNumber(mActivity);
        if (faceNum == 0){
            ToastUtils.ToastShort("没有记录需要删除");
        }else {
            AlertDialog dialog = new AlertDialog.Builder(mActivity)
                    .setTitle("提示")
                    .setMessage("确认删除这" + faceNum + "张图片及人脸特征？")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int deleteCount = FaceServer.getInstance().clearAllFaces(mActivity);
                            ToastUtils.ToastShort(deleteCount + "条记录删除成功");
                        }
                    })
                    .setNegativeButton("取消",null)
                    .create();
            dialog.show();
        }
    }

    @Override
    public void loadInfo(String avatarSrc, String userName) {

    }

    @OnClick({R.id.btn_join, R.id.btn01, R.id.btn02, R.id.btn03, R.id.btn04, R.id.avatar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn01:
                jumpToActivity(MyDemandActivity.class);
                break;
            case R.id.btn02:
                jumpToActivity(MyMeetingActivity.class);
                break;
            case R.id.btn03:
                jumpToActivity(MyCollectionActivity.class);
                break;
            case R.id.btn04:
                jumpToActivity(MyRecentlyViewActivity.class);
                break;
            case R.id.avatar:
                Intent intent = new Intent(mActivity, LoginActivity.class);
                mActivity.startActivityForResult(intent, LoginActivity.requestCode);
                break;
            case R.id.btn_join:
                jumpToActivity(JoinMeetingActivity.class);
                break;
        }
    }

    public void doRefresh() {
        if (App.iSLOGIN()) {
            Picasso.get()
                    .load("http://149.28.149.136:8080/image/user1.jpg")
                    .into(avatar);
            name.setText(App.getUserName());
            email.setText(App.getUserEmail());
            email.setVisibility(View.VISIBLE);
            avatar.setEnabled(false);
            tabLayout.setVisibility(View.VISIBLE);
        } else {
            avatar.setImageResource(R.mipmap.image_placeholder);
            avatar.setEnabled(true);
            name.setText("点击头像登录");
            email.setVisibility(View.GONE);
            email.setText("");
            tabLayout.setVisibility(View.GONE);
        }
    }

    private HomeActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (HomeActivity) context;
    }
}
