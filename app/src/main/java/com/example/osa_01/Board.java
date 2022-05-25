package com.example.osa_01;

public class Board {
    private String title;
    private String contents;
    private String Uid;

    public Board() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public Board(String Uid, String title, String contents){
        this.Uid = Uid;
        this.title = title;
        this.contents = contents;
    }
}
