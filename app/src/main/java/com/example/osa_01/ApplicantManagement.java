package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ApplicantManagement extends AppCompatActivity {
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter adapter2;
    private RecyclerView.LayoutManager layoutManager2;
    private ArrayList<TestApplicant> arraylist;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_management);

        Intent intent = getIntent();
        String t1 = intent.getStringExtra("board_num");

        recyclerView2 = findViewById(R.id.recyclerView_applicantlist);
        recyclerView2.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager2);
        arraylist = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Firebase").child("Applicant").child(t1).child("list");   //여기까진 문제없음

        databaseReference.addValueEventListener(new ValueEventListener() {  //addValue리스너로 값바뀔때마다 새로고침
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파베 디비 데이터 받는 단계
                arraylist.clear();      //찌꺼지 치우기
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TestApplicant applicant = snapshot.getValue(TestApplicant.class);  //디비에서 데이터 가져와서 Board객체로 담음

                    //String add_applicant = applicant.getApplicant();
                    arraylist.add(applicant);
                }
                adapter2.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //에러 발생시
            }
        });

        adapter2 = new ApplicantAdapter2(arraylist,this);     //어댑터 생성,무슨 어댑터 쓸건지 고르는거네
        recyclerView2.setAdapter(adapter2);       //생성 어댑터 리사이클러뷰로 쏴주기
    }
}