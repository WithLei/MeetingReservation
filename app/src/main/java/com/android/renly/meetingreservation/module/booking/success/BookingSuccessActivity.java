package com.android.renly.meetingreservation.module.booking.success;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.utils.LogUtils;

import butterknife.BindView;

public class BookingSuccessActivity extends BaseActivity {
    @BindView(R.id.area)
    TextView tvArea;
    @BindView(R.id.type)
    TextView tvType;
    @BindView(R.id.time)
    TextView tvTime;
    @BindView(R.id.people)
    TextView tvPeople;
    @BindView(R.id.budget)
    TextView tvBudget;
    @BindView(R.id.company)
    TextView tvCompany;
    @BindView(R.id.phone)
    TextView tvPhone;
    @BindView(R.id.demand)
    TextView tvDemand;

    private String area;
    private String type;
    private String date;
    private String time;
    private boolean isRescheduled;
    private String people;
    private String budget;
    private String company;
    private String phone;
    private String demand;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_booking_success;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        area = intent.getStringExtra("area");
        type = intent.getStringExtra("type");
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        isRescheduled = intent.getBooleanExtra("isRescheduled",false);
        people = intent.getStringExtra("people");
        budget = intent.getStringExtra("budget");
        company = intent.getStringExtra("company");
        phone = intent.getStringExtra("phone");
        demand = intent.getStringExtra("demand");
    }

    @Override
    protected void initView() {
        initToolBar(false, "需求订单详情");
        findViewById(R.id.menu).setOnClickListener(view -> finishActivityBottom());
        initSlidr();

        if (area == null || TextUtils.isEmpty(area)) {
            tvArea.setVisibility(View.GONE);
        } else {
            tvArea.setText("区域： " + area);
        }
        if (type == null || TextUtils.isEmpty(type)) {
            tvType.setVisibility(View.GONE);
        } else {
            tvType.setText("活动类型： " + type);
        }
        tvTime.setText("时间： " + date + " " + time);
        tvPeople.setText("活动人数： " + people + "人");
        if (budget == null || TextUtils.isEmpty(budget.trim())) {
            tvBudget.setVisibility(View.GONE);
        } else {
            tvBudget.setText("活动预算： " + budget + "元");
        }
        tvCompany.setText("联系人/公司： " + company);
        tvPhone.setText("联系方式： " + phone);
        if (demand == null || TextUtils.isEmpty(demand.trim())) {
            tvDemand.setVisibility(View.GONE);
        } else {
            tvDemand.setText("其他需求： " + demand);
        }
    }
}
