package com.example.osa_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateRecruitmentPage extends AppCompatActivity {
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스 친구
    private FirebaseAuth mFirebaseAuth; //파이어베이스어스 인증처리하는 친구
    private EditText edttitle, edtcontents;
    private String Uid; //유저정보
    private Button reg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recruitment_page);

        edttitle = (EditText)findViewById(R.id.title);
        edtcontents = (EditText) findViewById(R.id.contents);
        reg_btn = (Button)findViewById(R.id.button);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Firebase");

        if (user != null) {
            // User is signed in
            Uid = user.getEmail();
        } else {
            // No user is signed in
        }

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edttitle.getText().toString();
                //String user_id = Uid.toString();
                String contents = edtcontents.getText().toString();

                writeNewUser(Uid, title, contents);
            }
        });
    }

    public void writeNewUser(String Uid, String title, String contents) {
        Board board = new Board(Uid, title, contents);

        mDatabaseRef.child("Board").child(Uid).setValue(board);
    }
}