package com.example.osa_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스 친구
    private FirebaseAuth mFirebaseAuth; //파이어베이스어스 인증처리하는 친구
    TextView tv;
    String email;   //사용자 이메일
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 메모리에 레이아웃을 올려줌


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Firebase");

        if (user != null) {
            // User is signed in
            email = user.getEmail();
        } else {
            // No user is signed in
        }

        tv = (TextView)findViewById(R.id.Useremail);    //유저이메일 보여줄거
        tv.setText(email);  //가져온 유저이메일 세팅


        ImageButton button1 = findViewById(R.id.RecruitingPageMove);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RecruitingPage.class); //
                startActivityForResult(intent,101);
            }
        });

        ImageButton button2 = findViewById(R.id.CommunityPage);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CommunityPage.class); //
                startActivityForResult(intent,102);
            }
        });

        Button btn_menu = findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Mymenu.class); //
                startActivityForResult(intent,102);
            }
        });
    }
}