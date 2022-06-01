package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateRecruitmentPage extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private EditText edttitle, edtcontents;
    private Button reg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recruitment_page);

        edttitle = (EditText)findViewById(R.id.title);
        edtcontents = (EditText) findViewById(R.id.contents);
        reg_btn = (Button)findViewById(R.id.button);
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Firebase");

        reg_btn.setOnClickListener(new View.OnClickListener() { //등록 버튼 눌렀을 때
            @Override
            public void onClick(View view) {
                String title = edttitle.getText().toString();       //글제목, 내용 받아옴
                String contents = edtcontents.getText().toString();

                writeNewBoard(title, contents);     //인원모집 글 실행
            }
        });
    }

    public void writeNewBoard(String title, String contents) {      //인원모집 글쓰기 함수
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();   //현재 로그인한 유저 정보 가져옴
        Board board = new Board(mUser.getEmail(), title, contents);         //게시판 객체 생성

        mDatabaseRef.child("Board").child(mUser.getUid()).setValue(board);  //Board 아래 사용자고유토큰으로 구분해서 글 생성
    }
}