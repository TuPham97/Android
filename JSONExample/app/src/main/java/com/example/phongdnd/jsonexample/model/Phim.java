package com.example.phongdnd.jsonexample.model;

import android.graphics.drawable.Drawable;

public class Phim {
    private int MAPHIM;
    private String TENPHIM;
    private String HINHANH;
    private String NGAYCONGCHIEU;
    private String MOTA;
    private String GIA;

    public int getMAPHIM() {
        return MAPHIM;
    }

    public void setMAPHIM(int MAPHIM) {
        this.MAPHIM = MAPHIM;
    }

    public String getTENPHIM() {
        return TENPHIM;
    }

    public void setTENPHIM(String TENPHIM) {
        this.TENPHIM = TENPHIM;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String HINHANH) {
        this.HINHANH = HINHANH;
    }

    public String getNGAYCONGCHIEU() {
        return NGAYCONGCHIEU;
    }

    public void setNGAYCONGCHIEU(String NGAYCONGCHIEU) {
        this.NGAYCONGCHIEU = NGAYCONGCHIEU;
    }

    public String getMOTA() {
        return MOTA;
    }

    public void setMOTA(String MOTA) {
        this.MOTA = MOTA;
    }

    public String getGIA() {
        return GIA;
    }

    public void setGIA(String GIA) {
        this.GIA = GIA;
    }

    public Phim(int MAPHIM, String TENPHIM, String HINHANH, String NGAYCONGCHIEU, String MOTA, String GIA) {
        this.MAPHIM = MAPHIM;
        this.TENPHIM = TENPHIM;
        this.HINHANH = HINHANH;
        this.NGAYCONGCHIEU = NGAYCONGCHIEU;
        this.MOTA = MOTA;
        this.GIA = GIA;
    }

    public Phim() {
    }
}
