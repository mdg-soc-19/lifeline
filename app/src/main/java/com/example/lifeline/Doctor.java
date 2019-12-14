package com.example.lifeline;

public class Doctor {
    public int img;
    public String doc_name;
    public String doc_graduate;

    public Doctor(int img, String doc_name, String doc_graduate, String doc_dpt) {
        this.img = img;
        this.doc_name = doc_name;
        this.doc_graduate = doc_graduate;
        this.doc_dpt = doc_dpt;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDoc_graduate() {
        return doc_graduate;
    }

    public void setDoc_graduate(String doc_graduate) {
        this.doc_graduate = doc_graduate;
    }

    public String getDoc_dpt() {
        return doc_dpt;
    }

    public void setDoc_dpt(String doc_dpt) {
        this.doc_dpt = doc_dpt;
    }

    public String doc_dpt;

}
