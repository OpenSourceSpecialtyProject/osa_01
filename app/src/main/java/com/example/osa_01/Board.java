package com.example.osa_01;

public class Board {
    private String title;
    private String contents;
    private String Uid;
    private int board_total;
    private int board_current;
    private String applycant;
    private int board_number;

    public int getBoard_number() {
        return board_number;
    }

    public void setBoard_number(int board_number) {
        this.board_number = board_number;
    }

    public int getBoard_total() {
        return board_total;
    }

    public void setBoard_total(int board_total) {
        this.board_total = board_total;
    }

    public int getBoard_current() {
        return board_current;
    }

    public void setBoard_current(int board_current) {
        this.board_current = board_current;
    }

    public String getApplycant() {
        return applycant;
    }

    public void setApplycant(String applycant) {
        this.applycant = applycant;
    }

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

    public Board(String Uid, String title, String contents, int num){
        this.Uid = Uid;
        this.title = title;
        this.contents = contents;
        this.board_current = 1;
        this.board_total = 4;
        this.board_number = num;
        //this.applycant = null;
    }
}
