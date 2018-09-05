package com.example.user.at;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyInfoAdapter extends RecyclerView.Adapter<MyInfoViewHolder> {
    private Context myInfoContext;
    private ArrayList<MyInfoItem> items;

    MyInfoAdapter(ArrayList item){items=item;}

    @NonNull
    @Override
    public MyInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_info_recycler_item,parent,false);
        myInfoContext=parent.getContext();
        MyInfoViewHolder holder = new MyInfoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyInfoViewHolder holder, int position) {
        holder.vhTime.setText(items.get(position).times);
        holder.vhTitle.setText(items.get(position).titles);
        holder.vhWriter.setText(items.get(position).writers);
        holder.vhFeedback.setText(items.get(position).feedbacks);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
