package com.example.phongdnd.jsonexample.model;

public class Ve {
    private int MAVE;
    private int MASUAT;
    private int MARAP;
    private String NGAYDAT;
    private int MAGHE;
    private int MAKH;

    public  Ve(){}

    public Ve(int MAVE, int MASUAT, int MARAP, String NGAYDAT, int MAGHE, int MAKH) {
        this.MAVE = MAVE;
        this.MASUAT = MASUAT;
        this.MARAP = MARAP;
        this.NGAYDAT = NGAYDAT;
        this.MAGHE = MAGHE;
        this.MAKH = MAKH;
    }

    public int getMAVE() {
        return MAVE;
    }

    public void setMAVE(int MAVE) {
        this.MAVE = MAVE;
    }

    public int getMASUAT() {
        return MASUAT;
    }

    public void setMASUAT(int MASUAT) {
        this.MASUAT = MASUAT;
    }

    public int getMARAP() {
        return MARAP;
    }

    public void setMARAP(int MARAP) {
        this.MARAP = MARAP;
    }

    public String getNGAYDAT() {
        return NGAYDAT;
    }

    public void setNGAYDAT(String NGAYDAT) {
        this.NGAYDAT = NGAYDAT;
    }

    public int getMAGHE() {
        return MAGHE;
    }

    public void setMAGHE(int MAGHE) {
        this.MAGHE = MAGHE;
    }

    public int getMAKH() {
        return MAKH;
    }

    public void setMAKH(int MAKH) {
        this.MAKH = MAKH;
    }
}
