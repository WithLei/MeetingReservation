package com.android.renly.meetingreservation.module.booking.search;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.listener.MyOnDateSetListener;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.booking.roomList.RoomListActivity;
import com.android.renly.meetingreservation.module.map.MapActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {
    @BindView(R.id.btn_confirm)
    Button btn;
    @BindView(R.id.search)
    LinearLayout search;
    @BindView(R.id.date)
    EditText date;

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

    @OnClick({R.id.search, R.id.btn_confirm,R.id.calendar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                jumpToActivity(MapActivity.class);
                break;
            case R.id.btn_confirm:
                jumpToActivity(RoomListActivity.class);
                break;
            case R.id.calendar:
                Calendar calendar = Calendar.getInstance();
                MyOnDateSetListener myOnDateSetListener = new MyOnDateSetListener() {
                    @Override
                    public void afterSetDate(String days) {
                        date.setText(days);
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
