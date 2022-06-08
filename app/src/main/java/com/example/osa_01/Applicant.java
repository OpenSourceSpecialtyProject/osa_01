package com.example.osa_01;

public class Applicant {
    private String host_id;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    private String list;
    private int board_number;

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public int getBoard_number() {
        return board_number;
    }

    public void setBoard_number(int board_number) {
        this.board_number = board_number;
    }

    public Applicant() {}

    public Applicant(String host_id, int num){
        this.board_number = num;
        this.host_id = host_id;
        //this.applicant = applicant;
    }
}
