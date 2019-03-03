package com.android.renly.meetingreservation.module.booking.bookingroom;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.listener.MyOnDateSetListener;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.booking.success.BookingSuccessActivity;
import com.android.renly.meetingreservation.utils.LogUtils;
import com.android.renly.meetingreservation.widget.dialog.TimeRangePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class BookingRoomActivity extends BaseActivity {
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.people)
    EditText people;
    @BindView(R.id.budget)
    TextInputEditText budget;
    @BindView(R.id.company)
    TextInputEditText company;
    @BindView(R.id.phone)
    TextInputEditText phone;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_booking_rooom;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.exit, R.id.date, R.id.time, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit:
                finishActivityBottom();
                break;
            case R.id.date:
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
                        calendar.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;
            case R.id.time:
                TimeRangePickerDialog dialog = new TimeRangePickerDialog(this,
                        "13:00 - 17:00",
                        new TimeRangePickerDialog.ConfirmAction() {
                            @Override
                            public void onLeftClick() {
                            }

                            @Override
                            public void onRightClick(String startAndEndTime) {
                                time.setText(startAndEndTime);
                            }
                        });
                        dialog.show();
                break;
            case R.id.btn_confirm:
                if (!checkInput())
                    break;
                LogUtils.printLog("budget " + budget.getText().toString());
                LogUtils.printLog("people " + people.getText());
                Intent intent = new Intent(this, BookingSuccessActivity.class);
                intent.putExtra("date", date.getText());
                intent.putExtra("time", time.getText());
                intent.putExtra("people", people.getText()+"");
                intent.putExtra("budget",budget.getText().toString());
                intent.putExtra("company",company.getText().toString());
                intent.putExtra("phone",phone.getText().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.translate_in, R.anim.translate_out);

                finishActivityBottom();
                break;
        }
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(company.getText().toString().trim())) {
            company.setError("请输入联系人");
            return false;
        } else if (TextUtils.isEmpty(people.getText().toString().trim())) {
            people.setError("请输入联系方式");
            return false;
        }
        return !date.getText().equals("请选择日期") || !TextUtils.isEmpty(date.getText()) ||
                !time.getText().equals("请选择具体时间") || !TextUtils.isEmpty(time.getText()) ||
                people.getText() != null || !TextUtils.isEmpty(people.getText());
    }
}
