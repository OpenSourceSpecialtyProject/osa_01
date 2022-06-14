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
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

        Button btn_search = (Button)findViewById(R.id.btn_search);
        EditText search_keyword = (EditText) findViewById(R.id.edt_search);

        recyclerView = findViewById(R.id.recyclerView);  //레이아웃 리사이클러뷰 매핑
        recyclerView.setHasFixedSize(true); //리사이클러뷰 성능강화?
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();  //Board 객체를 담을 배열리스트 (어댑터쪽으로)

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Firebase").child("Board"); //DB테이블(테이블 종류 중 Board테이블 리스트) 연결


        databaseReference.addValueEventListener(new ValueEventListener() {  //addValue리스너로 값바뀔때마다 새로고침
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

        //인원모집 작성 액티비티 넘어가는 기능
        FloatingActionButton button1 = findViewById(R.id.CreateRecruitment);
        button1.setOnClickListener(new View.OnClickListener() { //모집글 작성 페이지 넘어감
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CreateRecruitmentPage.class); //
                startActivity(intent);
                finish();
            }
        });

        //검색기능 추가
        btn_search.setOnClickListener(new View.OnClickListener() {  //검색버튼 눌렀을 시
            @Override
            public void onClick(View view) {
                String keyword = search_keyword.getText().toString();      //사용자가 입력한 검색어 받아오고
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() { //이 이벤트는 클릭시 한번만 실행하는 것으로
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //파베 디비 데이터 받는 단계
                        arrayList.clear();      //찌꺼지 치우기
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Board board = snapshot.getValue(Board.class);  //디비에서 데이터 가져와서 Board객체로 담음
                            String title = board.getTitle();                //가져온 데이터 제목 비교위함
                            if(title.contains(keyword)){            //contains로 입력문자열이 포함되어있다면
                                arrayList.add(board);       //가져온 친구 배열리스트에 추가 후 리사이클러뷰로 보낼 준비
                            }

                        }
                        adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //에러 발생시
                    }
                });
            }
        });

        Button btn_ref = findViewById(R.id.btn_refresh);    //새로고침버튼
        btn_ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });

        adapter = new BoardAdapter(arrayList,this);     //어댑터 생성
        recyclerView.setAdapter(adapter);       //생성 어댑터 리사이클러뷰로 쏴주기
    }

    private void refresh(){ //화면 새로고침
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}