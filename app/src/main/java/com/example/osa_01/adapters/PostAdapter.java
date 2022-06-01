package com.example.osa_01.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.osa_01.R;
import com.example.osa_01.models.post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<post> arrayList;
    private Context context;


    public PostAdapter(ArrayList<post> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false);
        PostViewHolder holder = new PostViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Glide.with(holder.itemView).load(arrayList.get(position).getProfile()).into(holder.iv_profile);
        //holder.tv_id.setText(arrayList.get(position).getUID());
        holder.tv_id.setText(arrayList.get(position).getTitle());
        holder.tv_id.setText(arrayList.get(position).getContents());
    }

    @Override
    public int getItemCount() {
        return (arrayList !=null ? arrayList.size() : 0);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_id, tv_pw,tv_userName;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile=itemView.findViewById(R.id.iv_profile);
            this.tv_id=itemView.findViewById(R.id.tv_id);
            this.tv_pw=itemView.findViewById(R.id.tv_pw);
            this.tv_userName=itemView.findViewById(R.id.tv_username);
        }
    }
}
