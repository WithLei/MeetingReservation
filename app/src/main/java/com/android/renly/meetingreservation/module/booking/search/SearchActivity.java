package com.android.renly.meetingreservation.module.booking.search;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.booking.roomList.RoomListActivity;
import com.android.renly.meetingreservation.module.map.MapActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {
    @BindView(R.id.btn_confirm)
    Button btn;
    @BindView(R.id.search)
    LinearLayout search;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToolBar(true, "搜索场地");
        initSlidr();
        btn.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.search, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                jumpToActivity(MapActivity.class);
                break;
            case R.id.btn_confirm:
                jumpToActivity(RoomListActivity.class);
                break;
        }
    }
}
