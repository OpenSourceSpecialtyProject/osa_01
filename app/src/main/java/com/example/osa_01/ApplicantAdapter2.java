package com.example.osa_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ApplicantAdapter2 extends RecyclerView.Adapter<ApplicantAdapter2.CustomViewHolder> {

    private ArrayList<TestApplicant> arraylist;
    private Context context;

    public ApplicantAdapter2(ArrayList<TestApplicant> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.applicant_list.setText(arraylist.get(position).getApplicant());
    }

    @Override
    public int getItemCount() {
        return (arraylist != null ? arraylist.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView applicant_list;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.applicant_list = itemView.findViewById(R.id.applicant_list);
        }
    }
}
