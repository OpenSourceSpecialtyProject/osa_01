package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginPage extends AppCompatActivity {
    private EditText mEtEmail, mEtPwd;  //로그인 입력필드
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스 친구
    private FirebaseAuth mFirebaseAuth; //파이어베이스어스 인증처리하는 친구

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Firebase");

        mEtEmail = findViewById(R.id.editTextTextPersonName);
        mEtPwd = findViewById(R.id.editTextTextPassword);

        Button button =findViewById(R.id.Login);    //로그인버튼
        Button button1 = findViewById(R.id.Join);   //회원가입버튼

        button.setOnClickListener(new View.OnClickListener() {  //로그인버튼 눌렀을 때
            @Override
            public void onClick(View view) {
                //로그인 요청
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();

                //로그인하기
                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //로그인 성공!
                            Intent intent = new Intent(LoginPage.this, MainActivity.class);
                            startActivity(intent);
                            finish();   //로그인으로 넘어가면 현재 액티비티 파괴
                        }else{
                            Toast.makeText(LoginPage.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        button1.setOnClickListener(new View.OnClickListener() { //회원가입버튼 눌렀을 때 JoinPage 액티비티로 이동
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),JoinPage.class); //해당 액티비티로이동
                startActivity(intent);  //병혁이거 바꿈
            }
        });
    }
}