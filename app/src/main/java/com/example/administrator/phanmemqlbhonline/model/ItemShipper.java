package com.example.administrator.phanmemqlbhonline.model;

import java.io.Serializable;

/**
 * Created by Administrator on 11/6/2017.
 */

public class ItemShipper implements Serializable {
    private int id;
    private String tensp;
    private int giasp;
    private int sl;
    private String tenKh,sdt,diachi;

    public ItemShipper(int id, String tensp, int giasp, int sl, String tenKh, String sdt, String diachi) {
        this.id = id;
        this.tensp = tensp;
        this.giasp = giasp;
        this.sl = sl;
        this.tenKh = tenKh;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
