package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommunityPage extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private  ArrayList<Post> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_page);
        FloatingActionButton button = findViewById(R.id.WritePost);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WritePostPage.class); //버튼 클릭시
                startActivityForResult(intent,222);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // post 객체를 담을 어레이 리스트

        database = FirebaseDatabase.getInstance(); // 파이어베이스 연동

        databaseReference = database.getReference("Post"); // 테이블 연동작업

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 파이어베이스 db의 데이터를 받아오는 영역
                arrayList.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){ // 반복문으로 데이터 리스트 출력
                    Post post = snapshot1.getValue(Post.class); //만들어 둿던 post 객체에 데이터를 담는다   포스트에 담아서 파이어베이스에 올린후 snapshot1에 있는 내용을 다시 post로 넣어 array리스트에 넣음
                    arrayList.add(post);// 담은 데이터를 배열에 넣고 리사이클러뷰로 보낼준비
                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //디비 가져오던중 에러발생시
                Log.e("커뮤니티페이지",String.valueOf(error.toException())); //에러문 출력
            }
        });

        adapter = new PostAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);//리사이클러뷰에 어뎁터 연결
    }
    protected void onPause() {
        super.onPause();
        finish();
    }

}