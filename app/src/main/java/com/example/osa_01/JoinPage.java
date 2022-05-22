package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinPage extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스어스 인증처리하는 친구
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스 친구
    private EditText mEtEmail, mEtPwd;  //회원가입 입력필드
    private Button mBtnRegister;    //회원가입버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_page);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Firebase");

        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        mBtnRegister = findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();

                //Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(JoinPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //파이어베이스유저라는 객체를 만들어서 회원가입된 유저를 가져옴
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId(firebaseUser.getEmail());    //정확히 로그인된 유저의 이메일 가져옴, 확실한 정보
                            account.setPassword(strPwd);                    //사용자가 입력했던 것을 그대로 가져옴, 정보확인해서 맞는 정보인지 확인하는 단계라 입력정보를 가져옴!

                            //setValue : database에 삽입하는 행위  / 필드값 집어넣는 행위인듯
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            //회원가입 성공했을 경우!
                            Toast.makeText(JoinPage.this, "회원가입에 성공하셨습니다",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(JoinPage.this, "회원가입에 실패하셨습니다",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}