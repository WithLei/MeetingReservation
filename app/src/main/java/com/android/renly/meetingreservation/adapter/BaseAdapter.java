package com.android.renly.meetingreservation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.renly.meetingreservation.listener.ItemClickListener;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder>{
    private ItemClickListener itemListener;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        BaseViewHolder(View itemView) {
            super(itemView);
            if (itemListener != null)
                itemView.setOnClickListener(this);
        }

        void setData(int pos) {

        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(view, this.getAdapterPosition());
            }
        }
    }
}
