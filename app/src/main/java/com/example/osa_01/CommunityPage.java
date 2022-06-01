package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.osa_01.adapters.PostAdapter;
import com.example.osa_01.models.post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommunityPage extends AppCompatActivity  {
    private RecyclerView PostRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<post> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private PostAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_page);

        PostRecyclerView = findViewById(R.id.community_recyclerview);// 리사이클러뷰 매핑
        PostRecyclerView.setHasFixedSize(true);// 오류 최소화
        layoutManager = new LinearLayoutManager(this);//
        PostRecyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // 유저객체를 담을 어레이 리스트

        database=FirebaseDatabase.getInstance(); // 파이어베이스 안에 데이터를 가져옴

        databaseReference = database.getReference().child("Firebase").child("Post");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //파이어베이스 db 받아옴
                arrayList.clear(); // 기존배열 리스트 초기화
                for(DataSnapshot snapshot1 : snapshot.getChildren()){ //데이터 리스트 추출
                    post post = snapshot1.getValue(post.class); //만들어둔 user 객체 담음
                    arrayList.add(post); // 담은 데이터를 배열에 넣고 리시이클러뷸 보낼준비
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //디비 가져오던중 에리 발생시
            }
        });
                //.child("Firebase").child("Post"); //db테이블 테이블 종류 중 보드테이블 리스트 연결

        adapter = new PostAdapter(arrayList, this);
        PostRecyclerView.setAdapter(mAdapter); // 리사이클러뷰 어레이리스트 연결

        Button button = findViewById(R.id.writePost);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }


    private void refresh(){ //화면 새로고침
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

}