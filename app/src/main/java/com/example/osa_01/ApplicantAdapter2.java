package com.example.osa_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ApplicantAdapter2 extends RecyclerView.Adapter<ApplicantAdapter2.ApplicantViewHolder> {

    private ArrayList<Applicant> arrayList3;
    private Context context;

    public ApplicantAdapter2(ArrayList<Applicant> arrayList3, Context context) {
        this.arrayList3 = arrayList3;
        this.context = context;
    }

    @NonNull
    @Override
    public ApplicantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_item, parent, false);
        ApplicantViewHolder holder = new ApplicantViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantViewHolder holder, int position) {
        holder.applicant_list.setText(arrayList3.get(position).getList());
    }

    @Override
    public int getItemCount() {
        return (arrayList3 != null ? arrayList3.size() : 0);
    }

    public class ApplicantViewHolder extends RecyclerView.ViewHolder {
        TextView applicant_list;
        public ApplicantViewHolder(@NonNull View itemView) {
            super(itemView);
            this.applicant_list = itemView.findViewById(R.id.applicant_list);
        }
    }
}
