package com.android.renly.meetingreservation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.bean.Lecture;
import com.android.renly.meetingreservation.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LectureAdapter extends BaseAdapter {
    private List<Lecture> list;
    private Context context;

    public LectureAdapter(Context context, List<Lecture> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    protected BaseViewHolder getItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lecture_recommend, parent, false);
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
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.speaker)
        TextView speaker;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.totaltime)
        TextView totalTime;

        NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        void setData(int pos) {
            Lecture lecture = list.get(pos);
                Picasso.get()
                    .load(lecture.getImg())
                    .into(img);
            title.setText(lecture.getTitle());
            speaker.setText(lecture.getSpeaker());
            time.setText(DateUtils.dateToStringMeduim(new Date(lecture.getTime())));
            totalTime.setText(lecture.getTotalTime());
        }
    }
}
