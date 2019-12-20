package com.example.lifeline;

public class Token {
    private int Token_No;
    private String Doctor_name;

    public Token() {

    }



    public Token(int Token_No, String Doctor_name) {

        this.Token_No = Token_No;
        this.Doctor_name = Doctor_name;
    }

    public int getToken_No() {
        return Token_No;
    }

    public void setToken_No(int token_No) {
        this.Token_No = token_No;
    }

    public String getDoctor_name() {
        return Doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.Doctor_name = doctor_name;
    }
}
