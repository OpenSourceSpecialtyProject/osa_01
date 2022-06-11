package com.example.osa_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewPost extends AppCompatActivity {
    private TextView title,contents, uid,replyView;
    Intent intent;
    private String vTitle,vContents,vUid,replyco;
    private EditText reply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        intent = getIntent();
        vTitle = intent.getStringExtra("title");
        vContents = intent.getStringExtra("content");
        vUid = intent.getStringExtra("writer");

        replyView = findViewById(R.id.replyView);
        title=findViewById(R.id.viewpostTitle);
        contents = findViewById(R.id.viewPostContents);
        uid = findViewById(R.id.viewPostUid);

        title.setText(vTitle);
        contents.setText(vContents);
        uid.setText("작성자: " + vUid);

        reply = findViewById(R.id.replyEdit);
        Button button = findViewById(R.id.replyUpload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replyco = reply.getText().toString();
                replyView.append(replyco+"\n");
            }
        });
    }
}