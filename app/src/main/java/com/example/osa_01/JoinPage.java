package com.example.osa_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JoinPage extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; //파이어베이스어스 인증처리하는 친구
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스 친구
    private EditText mEtEmail, mEtPwd, mEtPwdcheck, mEtNickname, mEtBirth;  //회원가입 입력필드
    private Button mBtnRegister, mBtnLogin;    //회원가입버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_page);

        mFirebaseAuth = FirebaseAuth.getInstance(); //파베 객체받아옴
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Firebase"); //데이터베이스 객체

        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtPwdcheck = findViewById(R.id.et_pw_ck);
        mEtNickname = findViewById(R.id.et_nickname);
        mEtBirth = findViewById(R.id.et_birth);

        mBtnRegister = findViewById(R.id.btn_register);
        mBtnLogin = findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {    //회원가입버튼눌렀을때
            @Override
            public void onClick(View view) {
                //회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString();    //문자열로 입력값 받아오고
                String strPwd = mEtPwd.getText().toString();
                String strPwdcheck = mEtPwdcheck.getText().toString();
                String strNickname = mEtNickname.getText().toString();
                String strBirth = mEtBirth.getText().toString();

                if(strEmail.equals("") || strPwd.equals("")){
                    Toast.makeText(JoinPage.this, "제대로 입력해주세요ㅡ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!strPwd.equals(strPwdcheck)) {
                    Toast.makeText(JoinPage.this, "비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                //파베객체받아온거로 Firebase Auth 진행, 유저이메일비번(이메일) 인증해주는거 사용
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(JoinPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){   //결과값(실패햇는지 성공햇는지) task로 던져줌 (성공햇다면)
                            //파이어베이스유저라는 객체를 만들어서 회원가입된 유저를 가져옴
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();    //프로젝트의 유저어카운트 클래스에서 어카운트 객체 생성
                            account.setIdToken(firebaseUser.getUid());      //필드 새로 추가함
                            account.setEmailId(firebaseUser.getEmail());    //정확히 로그인된 유저의 이메일 가져옴, 확실한 정보
                            account.setPassword(strPwd);                    //사용자가 입력했던 것을 그대로 가져옴, 정보확인해서 맞는 정보인지 확인하는 단계라 입력정보를 가져옴!
                            account.setNickname(strNickname); //이런식으로 커스터마이징한거 가져오면 될듯
                            account.setBirth(strBirth);

                            //setValue : database에 삽입하는 행위  / 유저어카운트키 아래 필드값 집어넣는 행위인듯
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            //회원가입 성공했을 경우!
                            Toast.makeText(JoinPage.this, "회원가입에 성공하셨습니다",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                            startActivity(intent);
                        }
                        else{   //알아서 중복검사해줌
                            Toast.makeText(JoinPage.this, "회원가입에 실패하셨습니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}