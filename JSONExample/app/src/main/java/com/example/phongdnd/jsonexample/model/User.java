package com.example.phongdnd.jsonexample.model;

public class User {
    private int MAKH;
    private String USERNAME;
    private String PASS;
    private String HOTEN;
    private boolean GIOITINH;
    private String NGAYSINH;
    private String SDT;

    public User() {
    }

    public User(int MAKH, String USERNAME, String PASS, String HOTEN, boolean GIOITINH, String NGAYSINH, String SDT) {
        this.MAKH = MAKH;
        this.USERNAME = USERNAME;
        this.PASS = PASS;
        this.HOTEN = HOTEN;
        this.GIOITINH = GIOITINH;
        this.NGAYSINH = NGAYSINH;
        this.SDT = SDT;
    }

    public int getMAKH() {
        return MAKH;
    }

    public void setMAKH(int MAKH) {
        this.MAKH = MAKH;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public boolean getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(boolean GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

}
