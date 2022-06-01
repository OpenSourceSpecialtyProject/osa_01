package com.example.osa_01.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osa_01.R;
import com.example.osa_01.models.post;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<post> datas;
    private Context context;

    public PostAdapter(List<post> datas,Context context) {
        this.datas = datas;
        this.context = context;
    }

    /*public PostAdapter(List<post> datas) {
        this.datas = datas;
    }*/

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        post data = datas.get(position);
        holder.title.setText(data.getTitle());
        holder.contents.setText(data.getContents());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        private TextView title,contents;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_post_title);
            contents = itemView.findViewById(R.id.item_post_contents);
        }
    }
}
