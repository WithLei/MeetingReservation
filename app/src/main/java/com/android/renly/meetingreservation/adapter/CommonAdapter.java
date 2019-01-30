package com.android.renly.meetingreservation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.bean.Demand;
import com.android.renly.meetingreservation.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommonAdapter extends BaseAdapter {
    private List<Demand> demandList;
    private Context context;

    public CommonAdapter(Context context, List<Demand> demandList) {
        this.context = context;
        this.demandList = demandList;
    }

    @Override
    protected BaseViewHolder getItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_meeting, parent, false);
        NormalViewHolder viewHolder = new NormalViewHolder(view);
        LogUtils.printLog("getItemViewHolder!!!");
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return demandList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class NormalViewHolder extends BaseViewHolder {
        @BindView(R.id.updateTime)
        TextView updateTime;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.people)
        TextView people;
        @BindView(R.id.budget)
        TextView budget;

        NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        void setData(int pos) {
            Demand demand = demandList.get(pos);
            updateTime.setText(demand.getUpdateTime());
            date.setText(demand.getDate());
            time.setText(demand.getTime());
            people.setText(demand.getPeople() + "人");
            budget.setText("￥" + demand.getBudget());
        }
    }
}
