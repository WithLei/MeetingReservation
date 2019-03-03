package com.android.renly.meetingreservation.module.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.renly.meetingreservation.App;
import com.android.renly.meetingreservation.R;
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
import com.android.renly.meetingreservation.utils.toast.MyToast;
import com.android.renly.meetingreservation.utils.toast.ToastUtils;
import com.android.renly.meetingreservation.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

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
                ToastUtils.ToastShort("设置界面");
                break;
            case 1:
                ToastUtils.ToastShort("分享客户端");
                break;
            case 2:
                if (App.iSLOGIN()) {
                    App.setIsLogout();
                    MyToast.showText(App.getContext(),"退出登录成功", Toast.LENGTH_SHORT,true);
                    doRefresh();
                }
                else
                    ToastUtils.ToastShort("关于本程序");
                break;
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
                    .load("http://149.28.149.136:8080/image/user9.jpg")
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
