package com.example.osa_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.osa_01.models.post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WritePostPage extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance(); // 로그인 인증
    DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Firebase"); //객체를 가져옴
    private EditText mTitle, mContents;
    private String Uid; //유저정보

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post_page);

        mTitle = findViewById(R.id.title_et);
        mContents = findViewById(R.id.content_et);
        findViewById(R.id.reg_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(mAuth.getCurrentUser() !=null){
            Uid = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            String title = mTitle.getText().toString();
            //String user_id = Uid.toString();
            String contents = mContents.getText().toString();

            writeNewUser(Uid, title, contents);
        }
    }

    public void writeNewUser(String Uid, String title, String contents) {
        post post = new post(Uid, title, contents);
        mDatabaseRef.child("Post").child(Uid).setValue(post); // 포스트 노드 생성및 추가
    }
}