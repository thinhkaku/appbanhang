package com.example.administrator.phanmemqlbhonline.model;

import java.io.Serializable;

/**
 * Created by Administrator on 11/1/2017.
 */

public class Item_DanhSachHang implements Serializable {
    private int id;
    private String hinh;
    private String tenSp;
    private int giasp;
    private int soluong;
    private String mauSac;
    private String sizeSp;
    private String tenKhachHang;
    private String sDTKhachHang;
    private String diaChiKhachHang;

    public Item_DanhSachHang(int id,String hinh, String tenSp, int giasp, int soluong, String mauSac, String sizeSp, String tenKhachHang, String sDTKhachHang, String diaChiKhachHang) {
        this.hinh = hinh;
        this.id=id;
        this.tenSp = tenSp;
        this.giasp = giasp;
        this.soluong = soluong;
        this.mauSac = mauSac;
        this.sizeSp = sizeSp;
        this.tenKhachHang = tenKhachHang;
        this.sDTKhachHang = sDTKhachHang;
        this.diaChiKhachHang = diaChiKhachHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getSizeSp() {
        return sizeSp;
    }

    public void setSizeSp(String sizeSp) {
        this.sizeSp = sizeSp;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getsDTKhachHang() {
        return sDTKhachHang;
    }

    public void setsDTKhachHang(String sDTKhachHang) {
        this.sDTKhachHang = sDTKhachHang;
    }

    public String getDiaChiKhachHang() {
        return diaChiKhachHang;
    }

    public void setDiaChiKhachHang(String diaChiKhachHang) {
        this.diaChiKhachHang = diaChiKhachHang;
    }
}
