package com.example.administrator.phanmemqlbhonline.model;

import java.io.Serializable;

/**
 * Created by Administrator on 11/27/2017.
 */

public class ItemHangDaMua implements Serializable {
    private int id;
    private String tenSP;
    private int giaSP;
    private int soLuong;
    private String mauSac;
    private String size;
    private String hinh;

    public ItemHangDaMua(int id, String tenSP, int giaSP, int soLuong, String mauSac, String size, String hinh) {
        this.id = id;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
        this.mauSac = mauSac;
        this.size = size;
        this.hinh = hinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
