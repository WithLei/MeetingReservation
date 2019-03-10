package com.android.renly.meetingreservation.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.bean.Room;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.booking.roomArrangement.RoomArrangementActivity;
import com.android.renly.meetingreservation.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomAdapter extends BaseAdapter {
    private List<Room> list;
    private Context context;

    public RoomAdapter(Context context, List<Room> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    protected BaseViewHolder getItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
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
        @BindView(R.id.bigImg)
        ImageView bigImg;
        @BindView(R.id.tip1)
        TextView tip1;
        @BindView(R.id.tip2)
        TextView tip2;
        @BindView(R.id.tip3)
        TextView tip3;
        @BindView(R.id.area_and_name)
        TextView areaAndName;
        @BindView(R.id.score)
        TextView score;
        @BindView(R.id.ll_fire)
        LinearLayout llFire;
        @BindView(R.id.recommondTime)
        TextView recommondTime;
        @BindView(R.id.iv_needVerify)
        ImageView ivNeedVerify;
        @BindView(R.id.tv_needVerify)
        TextView tvNeedVerify;
        @BindView(R.id.recommond)
        LinearLayout recommond;
        @BindView(R.id.price)
        TextView tvPrice;

        NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        void setData(int pos) {
            Room room = list.get(pos);
            Picasso.get()
                    .load(room.getImg())
                    .into(bigImg);
            String keyWords[] = room.getKeyWords();
            if (keyWords[0] != null) {
                tip1.setText(keyWords[0]);
                tip1.setVisibility(View.VISIBLE);
                if (keyWords[1] != null) {
                    tip2.setText(keyWords[1]);
                    tip2.setVisibility(View.VISIBLE);
                    if (keyWords[2] != null) {
                        tip3.setText(keyWords[2]);
                        tip3.setVisibility(View.VISIBLE);
                    }
                }
            }
            areaAndName.setText(room.getArea() + " · " + room.getName());
            score.setText(room.getScore() + "");
            tvPrice.setText("￥" + room.getPrice());
//            fire.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_hot));
            for (int i = 0; i < room.getHot(); i++) {
                if (i > 5)
                    break;
                ImageView fire = new ImageView(context);
                fire.setImageResource(R.drawable.ic_hot);
//                ViewGroup.LayoutParams params = fire.getLayoutParams();
//                params.height = 20;
//                params.width = 20;
//                fire.setLayoutParams(params);
                llFire.addView(fire);
            }
            Date date = new Date(room.getRecommondTime());
            recommondTime.setText(DateUtils.dateToStringShort(date));
            if (room.isNeedVerify()) {
                ivNeedVerify.setImageResource(R.drawable.ic_review);
                tvNeedVerify.setText("需要进行审核，预定该会议室请提前操作");
            } else {
                ivNeedVerify.setImageResource(R.drawable.ic_quick);
                tvNeedVerify.setText("无需审核，闪电预定");
            }
            recommond.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, RoomArrangementActivity.class));
                    ((BaseActivity) context).overridePendingTransition(R.anim.bottomin, R.anim.bottomout);
                }
            });
        }
    }
}
