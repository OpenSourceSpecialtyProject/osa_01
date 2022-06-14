package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BoardInfoActivity extends AppCompatActivity {

    //private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String title;
    private ArrayList<String> applicantList;
    private String host_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_info);

        Intent intent = getIntent();
        String t1 = intent.getStringExtra("title");

        TextView tv_item_title = (TextView) findViewById(R.id.board_item_title);
        TextView tv_item_contents = (TextView) findViewById(R.id.board_item_contents);
        TextView tv_uid = (TextView) findViewById(R.id.tv_uid);
        Button btn_apply = (Button)findViewById(R.id.btn_apply);

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();   //현재 로그인한 유저 정보 가져옴
        String Uid = mUser.getEmail();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Firebase").child("Board").child(t1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board board = snapshot.getValue(Board.class);
                tv_item_title.setText(board.getTitle());
                tv_item_contents.setText(board.getContents());
                tv_uid.setText("작성자: " + board.getUid());
                host_id = board.getUid();
                title = board.getTitle();

                if(Uid.equals(board.getUid())){
                    btn_apply.setVisibility(View.INVISIBLE);
                }

                if(snapshot.hasChild("applicant")){
                    DatabaseReference applicantref = databaseReference.child("applicant");
                    applicantref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String applicant_id = snapshot.getValue().toString();    //문자열로 받기

                                /*
                                if(Uid.equals(applicant_id)){
                                    btn_apply.setVisibility(View.INVISIBLE);
                                }*/
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Getting Post failed, log a message
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { //참조에 액세스 할 수 없을 때 호출
                Toast.makeText(getApplicationContext(),"데이터를 가져오는데 실패했습니다" , Toast.LENGTH_LONG).show();
            }
        });

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int board_num = Integer.parseInt(t1);

                Applicant applicant = new Applicant(host_id, board_num);
                Apply apply = new Apply(title, Uid, host_id);
                databaseReference.child("Firebase").child("Board").child(t1).child("applicant").push().setValue(Uid);
                databaseReference.child("Firebase").child("Applicant").child(t1).setValue(applicant);
                databaseReference.child("Firebase").child("Applicant").child(t1).child("list").push().setValue(Uid);
                databaseReference.child("Apply").push().setValue(apply);

                Toast.makeText(BoardInfoActivity.this, "신청완료!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), RecruitingPage.class); //
                startActivity(intent);
                finish();
            }
        });
    }
}