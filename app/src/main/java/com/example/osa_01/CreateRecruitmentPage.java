package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CreateRecruitmentPage extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private EditText edttitle, edtcontents;
    private Button reg_btn;

    private int board_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recruitment_page);

        edttitle = (EditText)findViewById(R.id.title);
        edtcontents = (EditText) findViewById(R.id.contents);
        reg_btn = (Button)findViewById(R.id.button);
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Firebase");

        Query boardQuery = mDatabaseRef.child("Board").orderByKey().limitToLast(1);     //보드 테이블에서 board_num 제일 큰거 가져옴

        boardQuery.addChildEventListener(new ChildEventListener(){      //차일드 리스너
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Board board = snapshot.getValue(Board.class);  //디비에서 데이터 가져와서 Board객체로 담음
                board_num = board.getBoard_number();            //보드넘 세팅
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        int number = board_num+1;                       //게시글 분류를 위한 고유번호
        Board board = new Board(mUser.getEmail(), title, contents, number);         //게시판 객체 생성


        String num = String.valueOf(number);            //해당 게시글 db올리기위해서 문자열로 변환
        mDatabaseRef.child("Board").child(num).setValue(board);  //Board 아래 게시판넘버(사용자고유토큰-예전)로 구분해서 글 생성
    }
}