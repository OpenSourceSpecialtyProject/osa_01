package com.example.osa_01;

public class UserAccount {
    //게터세터! 필드 추가는 추후 알아서 하길
    private String idToken; //Firebase Uid (고유 토큰정보) - 사용자 하나의 정보만 유일하게 가질 수 있는 Key값!
    private String emailId; //이메일 아디
    private String password; //비번
    private String passwordCheck;
    private String nickname;
    private String birth;

    //생성자, 파이어베이스 실시간 DB 쓸 때는 생성자메소드 꼭 필요 /  규칙
    public UserAccount() {}

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
