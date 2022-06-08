package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Mymenu extends AppCompatActivity {
        private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Board> arrayList2;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymenu);

        recyclerView = findViewById(R.id.recyclerView_mymenu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList2 = new ArrayList<>();  //

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Firebase").child("Board");

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();   //현재 로그인한 유저 정보 가져옴
        String Uid = mUser.getEmail();

        databaseReference.addValueEventListener(new ValueEventListener() {  //addValue리스너로 값바뀔때마다 새로고침
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파베 디비 데이터 받는 단계
                arrayList2.clear();      //찌꺼지 치우기
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Board board = snapshot.getValue(Board.class);  //디비에서 데이터 가져와서 Board객체로 담음
                    //arrayList2.add(board);       //가져온 친구 배열리스트에 추가 후 리사이클러뷰로 보낼 준비
                    String Board_Uid = board.getUid();                //가져온 데이터 제목 비교위함

                    if(Uid.equals(Board_Uid)){            //contains로 입력문자열이 포함되어있다면
                        arrayList2.add(board);       //가져온 친구 배열리스트에 추가 후 리사이클러뷰로 보낼 준비
                    }
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //에러 발생시
            }
        });

        adapter = new ApplicantAdapter(arrayList2,this);     //어댑터 생성,무슨 어댑터 쓸건지 고르는거네
        recyclerView.setAdapter(adapter);       //생성 어댑터 리사이클러뷰로 쏴주기
    }
}