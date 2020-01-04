package com.example.lifeline;

public class Last_token {
    private int Last_Token_No;
    private String Last_Doctor_name;

    public Last_token() {

    }
    public Last_token(int Last_Token_No, String Last_Doctor_name) {

        this.Last_Token_No = Last_Token_No;
        this.Last_Doctor_name = Last_Doctor_name;
    }

    public int getLast_Token_No() {
        return Last_Token_No;
    }

    public void setLast_Token_No(int last_Token_No) {
        Last_Token_No = last_Token_No;
    }

    public String getLast_Doctor_name() {
        return Last_Doctor_name;
    }

    public void setLast_Doctor_name(String last_Doctor_name) {
        Last_Doctor_name = last_Doctor_name;
    }
}
