package com.example.osa_01;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class replyadapter extends RecyclerView.Adapter<replyadapter.PostViewHolder> {

    private ArrayList<reply> arrayList;
    private Context context;
    private String title, content, Uid;

    public replyadapter(ArrayList<reply> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // 실제 리스트뷰가 연결이됫을때 뷰홀더 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false);
        PostViewHolder holder = new PostViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) { // 각 아이템에 대한 실질적인 매칭
        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_contents.setText(arrayList.get(position).getContents());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_contents;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_contents = itemView.findViewById(R.id.tv_contents);
        }
    }
}
