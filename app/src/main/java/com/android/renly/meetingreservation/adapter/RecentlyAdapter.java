package com.android.renly.meetingreservation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.bean.Room;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecentlyAdapter extends BaseAdapter {
    private List<Room> list;
    private Context context;

    public RecentlyAdapter(Context context, List<Room> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    protected BaseViewHolder getItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recentlyview, parent, false);
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
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.capacity_and_people)
        TextView capacity_and_people;
        @BindView(R.id.area)
        TextView area;
        @BindView(R.id.hot)
        LinearLayout hot;

        NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        void setData(int pos) {
            Room room = list.get(pos);
            name.setText(room.getName());
            Picasso.get()
                    .load(room.getImg())
                    .into(img);
            capacity_and_people.setText(room.getCapacity() + " | 可容纳" + room.getPeople() + "人");
            area.setText(room.getArea());
            for (int i = 0;i < room.getHot();i++) {
                if (i > 5)
                    break;
                ImageView fire = new ImageView(context);
                fire.setImageResource(R.drawable.ic_hot);
                hot.addView(fire);
            }
        }
    }
}
