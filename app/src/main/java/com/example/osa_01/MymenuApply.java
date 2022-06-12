package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


//마이메뉴에서 나의 신청현황 보는 액티비티
public class MymenuApply extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Apply> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymenu_apply);

        recyclerView = findViewById(R.id.recyclerView_mymenu_apply);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();  //


        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();   //현재 로그인한 유저 정보 가져옴
        String Uid = mUser.getEmail();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Apply");


        Button button2 = findViewById(R.id.button_party2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Mymenu.class);
                startActivity(intent);
                finish();
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {  //addValue리스너로 값바뀔때마다 새로고침
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파베 디비 데이터 받는 단계
                arrayList.clear();      //찌꺼지 치우기
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Apply apply = snapshot.getValue(Apply.class);  //디비에서 데이터 가져와서 Board객체로 담음

                    if(Uid.equals(apply.getApplicant())) {
                        arrayList.add(apply);
                    }
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //에러 발생시
            }
        });



        adapter = new ApplyAdapter(arrayList,this);     //어댑터 생성,무슨 어댑터 쓸건지 고르는거네
        recyclerView.setAdapter(adapter);       //생성 어댑터 리사이클러뷰로 쏴주기
    }
}