package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecruitingPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Board> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiting_page);

        recyclerView = findViewById(R.id.recyclerView);  //레이아웃 리사이클러뷰 매핑
        recyclerView.setHasFixedSize(true); //리사이클러뷰 성능강화?
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();  //Board 객체를 담을 배열리스트 (어댑터쪽으로)

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Firebase").child("Board"); //DB테이블(테이블 종류 중 Board테이블 리스트) 연결

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파베 디비 데이터 받는 단계
                arrayList.clear();      //찌꺼지 치우기
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Board board = snapshot.getValue(Board.class);  //디비에서 데이터 가져와서 Board객체로 담음
                    arrayList.add(board);       //가져온 친구 배열리스트에 추가 후 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //에러 발생시
            }
        });

        Button button1 = findViewById(R.id.CreateRecruitment);
        button1.setOnClickListener(new View.OnClickListener() { //모집글 작성 페이지 넘어감
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CreateRecruitmentPage.class); //
                startActivity(intent);
            }
        });


        Button btn_ref = findViewById(R.id.btn_refresh);    //새로고침버튼
        btn_ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });

        adapter = new BoardAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);


    }

    private void refresh(){ //화면 새로고침
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}