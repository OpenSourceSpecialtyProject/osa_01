package com.example.osa_01.models;

public class post {

    private String profile;
    private String UID;
    private String title;
    private String contents;

    public post(){}

    public post(String title, String contents) {
        this.title = title;
        this.contents=contents;
    }


    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
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
