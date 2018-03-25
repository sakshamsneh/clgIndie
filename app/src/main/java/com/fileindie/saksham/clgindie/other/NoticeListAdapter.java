package com.fileindie.saksham.clgindie.other;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fileindie.saksham.clgindie.R;

import java.util.List;

/**
 * Created by SAKSHIM on 22/03/2018.
 */

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.MyViewHolder> {
    private List<NoticeList> noticeLists;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public NoticeListAdapter(List<NoticeList> noticeLists) {
        this.noticeLists=noticeLists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notice_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NoticeList noticeList = noticeLists.get(position);
        holder.title.setText(noticeList.getTitle());
        holder.date.setText(noticeList.getDate());
    }

    @Override
    public int getItemCount() {
        return noticeLists.size();
    }
}
