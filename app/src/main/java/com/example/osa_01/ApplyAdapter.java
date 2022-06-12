package com.example.osa_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ApplyAdapter extends RecyclerView.Adapter<ApplyAdapter.ApplyViewHolder> {
    private ArrayList<Apply> arrayList;
    private Context context;

    public ApplyAdapter(ArrayList<Apply> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ApplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mymenu_item, parent, false);
        ApplyViewHolder holder = new ApplyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ApplyViewHolder holder, int position) {
        holder.menu_board_title.setText(arrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class ApplyViewHolder extends RecyclerView.ViewHolder {
        TextView menu_board_title;
        public ApplyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.menu_board_title = itemView.findViewById(R.id.menu_board_title);
        }
    }
}
