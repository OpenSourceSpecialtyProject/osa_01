package com.example.osa_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BoardInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_info);

        Intent intent = getIntent();
        String t1 = intent.getStringExtra("title");

        TextView tv_item_title = (TextView) findViewById(R.id.board_item_title);
        tv_item_title.setText(t1);
    }
}