package com.android.renly.meetingreservation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.bean.Demand;
import com.android.renly.meetingreservation.utils.LogUtils;
import com.android.renly.meetingreservation.utils.toast.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemandAdapter extends BaseAdapter {
    private List<?> list;
    private Context context;

    public DemandAdapter(Context context, List<?> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    protected BaseViewHolder getItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_demand, parent, false);
        NormalViewHolder viewHolder = new NormalViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
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
        @BindView(R.id.btn)
        Button btn;

        NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        void setData(int pos) {
            // 需求列表
            if (list.get(pos) instanceof Demand){
                Demand demand = (Demand) list.get(pos);
                updateTime.setText(demand.getUpdateTime());
                date.setText(demand.getDate());
                time.setText(demand.getTime());
                people.setText(demand.getPeople() + "人");
                budget.setText("￥" + demand.getBudget());
                btn.setOnClickListener(view -> {
                    btn.setEnabled(false);
                    btn.setText("已催单");
                    ToastUtils.ToastShort("已为您进行催单~");
                });
            }

            //
        }
    }
}
