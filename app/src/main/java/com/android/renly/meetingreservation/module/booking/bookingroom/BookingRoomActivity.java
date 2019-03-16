package com.android.renly.meetingreservation.module.booking.bookingroom;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.listener.MyOnDateSetListener;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.booking.roomArrangement.RoomArrangementActivity;
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
    @BindView(R.id.company)
    TextInputEditText company;
    @BindView(R.id.phone)
    TextInputEditText phone;
    @BindView(R.id.fee_content)
    LinearLayout feeContent;
    @BindView(R.id.fee_pay_hour)
    TextView feePayHour;
    @BindView(R.id.fee_hour)
    TextView feeHour;
    @BindView(R.id.fee_pay)
    TextView feePay;
    @BindView(R.id.translation)
    TextView translation;
    @BindView(R.id.fee_translation)
    TextView feeTranslation;
    @BindView(R.id.fee_total)
    TextView feeTotal;
    @BindView(R.id.pay)
    TextView payTotal;

    private String date_str;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_booking_rooom;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        updateSelectDate(getIntent());
    }

    @OnClick({R.id.exit, R.id.date, R.id.time, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit:
                finishActivityBottom();
                break;
            case R.id.date:
                jumpToActivityBottom(RoomArrangementActivity.class);
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

                                feeContent.setVisibility(View.VISIBLE);
                                payTotal.setVisibility(View.VISIBLE);
                            }
                        });
                        dialog.show();
                break;
            case R.id.btn_confirm:
                if (!checkInput())
                    break;
                Intent intent = new Intent(this, BookingSuccessActivity.class);
                intent.putExtra("date", date.getText());
                intent.putExtra("time", time.getText());
                intent.putExtra("people", people.getText()+"");
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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        updateSelectDate(intent);
    }

    /**
     * 当接受到新的intent时更新date
     */
    private void updateSelectDate(Intent intent) {
        date_str = intent.getStringExtra("date");
        if (date_str != null && !TextUtils.isEmpty(date_str.trim())) {
            date.setText(date_str);
        }
    }
}
