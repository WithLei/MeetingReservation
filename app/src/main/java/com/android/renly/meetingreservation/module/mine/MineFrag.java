package com.android.renly.meetingreservation.module.mine;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.renly.meetingreservation.App;
import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.injector.components.DaggerMineFragComponent;
import com.android.renly.meetingreservation.injector.modules.MineFragModule;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.utils.toast.ToastUtils;
import com.android.renly.meetingreservation.widget.CircleImageView;

import javax.inject.Inject;

import butterknife.BindView;

public class MineFrag extends BaseFragment
        implements AdapterView.OnItemClickListener, MineFragView{
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
                ToastUtils.ToastShort("关于本程序");
                break;
        }
    }

    @Override
    public void loadInfo(String avatarSrc, String userName) {

    }
}
