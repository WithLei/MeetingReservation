package com.android.renly.meetingreservation.module.booking;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.listener.MyOnDateSetListener;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.booking.success.BookingSuccessActivity;
import com.android.renly.meetingreservation.utils.toast.ToastUtils;
import com.android.renly.meetingreservation.widget.dialog.MyDialogView;
import com.android.renly.meetingreservation.widget.dialog.MyRadioDialogView;
import com.android.renly.meetingreservation.widget.dialog.TimeRangePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingFrag extends BaseFragment {
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.isRescheduled)
    Switch isRescheduled;
    @BindView(R.id.people)
    TextInputEditText people;
    @BindView(R.id.budget)
    TextInputEditText budget;
    @BindView(R.id.company)
    TextInputEditText company;
    @BindView(R.id.phone)
    TextInputEditText phone;
    @BindView(R.id.demand)
    EditText demand;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_type)
    TextView tvType;

    @Override
    protected void initInjector() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.frag_booking;
    }

    @Override
    protected void initData(Context content) {
    }

    @Override
    protected void initView() {
        btn.setEnabled(true);
    }

    @Override
    public void ScrollToTop() {

    }

    @OnClick({R.id.contact, R.id.area, R.id.type, R.id.date, R.id.time, R.id.isRescheduled, R.id.people, R.id.budget, R.id.company, R.id.phone, R.id.demand, R.id.btn})
    public void onViewClicked(View view) {
        MyRadioDialogView myRadioDialogView = null;
        View mView;
        switch (view.getId()) {
            case R.id.contact:
                break;
            case R.id.area:
                mView = View.inflate(getContext(), R.layout.layout_area_dialog, null);;
                myRadioDialogView = new MyRadioDialogView(getContext(), mView, true, true);
                myRadioDialogView.setOnBtnListener(text -> {
                    tvArea.setText(text);
                    tvArea.setTextColor(getResources().getColor(R.color.text_color_pri));
                });
                myRadioDialogView.create();
                myRadioDialogView.show();
                break;
            case R.id.type:
                mView = View.inflate(getContext(), R.layout.layout_type_dialog, null);
                myRadioDialogView = new MyRadioDialogView(getContext(), mView, true, true);
                myRadioDialogView.setOnBtnListener(text -> {
                    tvType.setText(text);
                    tvType.setTextColor(getResources().getColor(R.color.text_color_pri));
                });
                myRadioDialogView.create();
                myRadioDialogView.show();
                break;
            case R.id.date:
                Calendar calendar = Calendar.getInstance();
                MyOnDateSetListener myOnDateSetListener = new MyOnDateSetListener() {
                    @Override
                    public void afterSetDate(String days) {
                        tvDate.setText(days);
                        tvDate.setTextColor(getResources().getColor(R.color.text_color_pri));
                    }
                };
                new DatePickerDialog(getContext(),
                        myOnDateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.time:
                TimeRangePickerDialog dialog = new TimeRangePickerDialog(getContext(), "13:00 - 17:00", new TimeRangePickerDialog.ConfirmAction() {
                    @Override
                    public void onLeftClick() {
                    }

                    @Override
                    public void onRightClick(String startAndEndTime) {
                        tvTime.setText(startAndEndTime);
                        tvTime.setTextColor(getResources().getColor(R.color.text_color_pri));
                    }
                });

                dialog.show();
                break;
            case R.id.btn:
                jumpToActivity(BookingSuccessActivity.class);
                break;
        }
    }

    public void loseFocus(){
        people.setFocusable(false);
        people.setFocusable(true);
        people.setFocusableInTouchMode(true);
        budget.setFocusable(false);
        budget.setFocusable(true);
        budget.setFocusableInTouchMode(true);
        company.setFocusable(false);
        company.setFocusable(true);
        company.setFocusableInTouchMode(true);
        phone.setFocusable(false);
        phone.setFocusable(true);
        phone.setFocusableInTouchMode(true);
        demand.setFocusable(false);
        demand.setFocusable(true);
        demand.setFocusableInTouchMode(true);
    }
}
