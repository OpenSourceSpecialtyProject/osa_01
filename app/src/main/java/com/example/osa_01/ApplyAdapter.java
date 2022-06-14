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

            itemView.setOnClickListener(new View.OnClickListener() {
                //private boolean et_artName;   //리사이클러뷰 클릭이벤트 처리 메소드
                @Override
                public void onClick(View v) {
                    //int currentPos = getAbsoluteAdapterPosition(); //각 어댑터의 위치 클릭된
                    //Board board = arrayList2.get(currentPos);

                    Intent intent =  new Intent(context, ChatMain.class);   //화면 넘겨주기
                    //intent.putExtra("board_num", String.valueOf(board.getBoard_number()));

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
