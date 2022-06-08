package com.example.osa_01;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.ApplicantViewHolder> {
    private ArrayList<Board> arrayList2;
    private Context context;

    public ApplicantAdapter(ArrayList<Board> arrayList, Context context) {
        this.arrayList2 = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ApplicantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mymenu_item, parent, false);
        ApplicantViewHolder holder = new ApplicantViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantViewHolder holder, int position) {
        holder.menu_board_title.setText(arrayList2.get(position).getTitle());
        //holder.host_id.setText(arrayList.get(position).getUid());
    }

    @Override
    public int getItemCount() {
        return (arrayList2 != null ? arrayList2.size() : 0);
    }

    public class ApplicantViewHolder extends RecyclerView.ViewHolder {
        TextView menu_board_title;
        //TextView host_id;

        public ApplicantViewHolder(@NonNull View itemView) {
            super(itemView);
            this.menu_board_title = itemView.findViewById(R.id.menu_board_title);
            //this.host_id = itemView.findViewById(R.id.host_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                //private boolean et_artName;   //리사이클러뷰 클릭이벤트 처리 메소드
                @Override
                public void onClick(View v) {
                    int currentPos = getAbsoluteAdapterPosition(); //각 어댑터의 위치 클릭된
                    Board board = arrayList2.get(currentPos);

                    Intent intent =  new Intent(context, ApplicantManagement.class);   //화면 넘겨주기
                    intent.putExtra("title", String.valueOf(board.getBoard_number()));

                    context.startActivity(intent);

                    /*
                    Toast.makeText(context, board.getTitle() +
                            "\n" + board.getContents()
                            , Toast.LENGTH_SHORT).show();*/   //해당위치 아이템 클릭시 토스트메시지창 띄우기
                }
            });
        }
    }
}
