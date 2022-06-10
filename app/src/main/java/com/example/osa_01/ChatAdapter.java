package com.example.osa_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private ArrayList<Chat> mDataset;
    private String user_id;
    private Context context;

    public ChatAdapter(ArrayList<Chat> mDataset, Context context, String user_id) {
        this.mDataset = mDataset;
        this.context = context;
        this.user_id = user_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Chat chat = mDataset.get(position);

        holder.chat_msg.setText(chat.getMessages());
        holder.chat_time.setText(chat.getTime());

        if(chat.getUser_id().equals(this.user_id)){     //오른쪽 배치 왼쪽배치 구분
            holder.chat_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.chat_time.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else{
            holder.chat_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.chat_time.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }
    }

    @Override
    public int getItemCount() {
        return (mDataset != null ? mDataset.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView chat_msg;
        public TextView chat_time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.chat_msg = itemView.findViewById(R.id.chat_msg);
            this.chat_time = itemView.findViewById(R.id.chat_time);
        }
    }

    public Chat getChat(int position){
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(Chat chat){
        mDataset.add(chat);
        notifyItemInserted(mDataset.size());
    }
}
