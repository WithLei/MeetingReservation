package com.android.renly.meetingreservation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.bean.SimpleUser;
import com.android.renly.meetingreservation.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends BaseAdapter {
    private Context mContext;
    private List<SimpleUser> list;

    public UserAdapter(Context mContext, List<SimpleUser> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_user, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        SimpleUser user = list.get(i);
        Picasso.get()
                .load(user.getAvatar())
                .placeholder(R.mipmap.user_placeholder)
                .into(holder.userAvatar);
        holder.userName.setText(user.getName());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.user_avatar)
        CircleImageView userAvatar;
        @BindView(R.id.user_name)
        TextView userName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
