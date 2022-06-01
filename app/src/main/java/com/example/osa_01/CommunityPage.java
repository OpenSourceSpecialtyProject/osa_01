package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class CommunityPage extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mPostRecyclerView;
    private RecyclerView.Adapter adapter;
    private FirebaseDatabase database;
    private PostAdapter mAdapter;
    private List<post> mDatas;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_page);
        mPostRecyclerView = findViewById(R.id.community_recyclerview);
        mPostRecyclerView.setHasFixedSize(true);
        mDatas = new ArrayList<>();

        database=FirebaseDatabase.getInstance(); // 파이어베이스 안에 데이터를 가져옴
        databaseReference = database.getReference().child("Firebase").child("Post"); //db테이블 테이블 종류 중 보드테이블 리스트 연결

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 데이터가 변하면 값을 받아와야함
                mDatas.clear(); // 초기화
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    post post = snapshot1.getValue(post.class);
                    mDatas.add(post);
                }
                adapter.notifyDataSetChanged(); // 리스트 저장및 새로 고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //에러발생시
            }
        });


        String title = "title";
        String contents = "contents";
        mDatas.add(new post(null,title,contents));
        mDatas.add(new post(null,title,contents));
        mDatas.add(new post(null,title,contents));
        mAdapter = new PostAdapter(mDatas, this);
        mPostRecyclerView.setAdapter(mAdapter);

      findViewById(R.id.writePost).setOnClickListener(this);


        adapter = new PostAdapter(mDatas,this);
        mPostRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, WritePostPage.class));
    }


}