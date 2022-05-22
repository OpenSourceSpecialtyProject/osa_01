package com.example.osa_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 메모리에 레이아웃을 올려줌

        Button button1 = findViewById(R.id.RecruitingPageMove);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RecruitingPage.class); //
                startActivityForResult(intent,101);
            }
        });

        Button button2 = findViewById(R.id.CommunityPage);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CommunityPage.class); //
                startActivityForResult(intent,102);
            }
        });
    }
}