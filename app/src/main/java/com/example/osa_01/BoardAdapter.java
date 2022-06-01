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

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder>{

    private ArrayList<Board> arrayList;
    private Context context;

    public BoardAdapter(ArrayList<Board> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //보드아이템 레이아웃에서 아이템가져옴
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item, parent, false);
        BoardViewHolder holder = new BoardViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        //holder.bd_UId.setText(arrayList.get(position).getUid());
        holder.bd_contents.setText(arrayList.get(position).getContents());
        holder.bd_title.setText(arrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        TextView bd_UId;
        TextView bd_contents;
        TextView bd_title;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            //아이템뷰에 가져오기
            //this.bd_UId = itemView.findViewById(R.id.bd_hostID);
            this.bd_contents = itemView.findViewById(R.id.bd_contents);
            this.bd_title = itemView.findViewById(R.id.bd_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                private boolean et_artName;   //리사이클러뷰 클릭이벤트 처리 메소드
                @Override
                public void onClick(View v) {
                    int currentPos = getAbsoluteAdapterPosition(); //각 어댑터의 위치 클릭된
                    Board board = arrayList.get(currentPos);

                    Intent intent =  new Intent(context, BoardInfoActivity.class);   //화면 넘겨주기
                    intent.putExtra("title", board.getTitle());

                    context.startActivity(intent);

                    Toast.makeText(context, board.getTitle() +
                            "\n" + board.getContents()
                            , Toast.LENGTH_SHORT).show();   //해당위치 아이템 클릭시 토스트메시지창 띄우기
                }
            });
        }
    }
}
