package com.example.osa_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WritePostPage extends AppCompatActivity  {

    ///private FirebaseAuth mAuth = FirebaseAuth.getInstance(); // 로그인 인증
    DatabaseReference DatabaseRef = FirebaseDatabase.getInstance().getReference("Post"); //객체를 가져옴
    private EditText Title, Contents;
    //private String Uid; //유저정보

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post_page);

        Title = findViewById(R.id.title_et);
        Contents = findViewById(R.id.content_et);

        Button button = findViewById(R.id.reg_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = Title.getText().toString();
                String contents = Contents.getText().toString();

                WritePost(title,contents);
                Intent intent = new Intent(getApplicationContext(),CommunityPage.class); //
                startActivity(intent);
                finish();
            }
        });

    }

    public void WritePost(String title, String contents){
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        Post post = new Post(mUser.getEmail(),title,contents);

        DatabaseRef.child(mUser.getUid()).setValue(post);
        Toast.makeText(WritePostPage.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),CommunityPage.class); //
        startActivityForResult(intent,333);
    }

}