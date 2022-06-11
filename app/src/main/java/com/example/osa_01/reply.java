package com.example.osa_01;

public class reply {
    private String Uid;
    private String title;
    private String contents;

    public reply(){}

    public reply(String Uid, String title, String contents){
        this.Uid=Uid;
        this.title = title;
        this.contents = contents;
    }

    public reply( String contents){
        this.contents = contents;
    }


    public reply(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

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
}
