package com.example.osa_01;

public class Apply {
    private String title;
    private String host_id;

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String applicant;

    public Apply() {
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Apply(String title, String Uid, String host_id) {
        this.title = title;
        this.applicant = Uid;
        this.host_id = host_id;
    }
}
