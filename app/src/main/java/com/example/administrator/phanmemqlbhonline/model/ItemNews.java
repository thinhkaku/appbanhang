package com.example.administrator.phanmemqlbhonline.model;

/**
 * Created by Administrator on 10/18/2017.
 */

public class ItemNews {
    private int id;
    private String hinhSpChinh;
    private String tenSp;
    private int giaSp;
    private String hinh1;
    private String hinh2;
    private String hinh3;
    private String hinh4;
    private String moTaSp;
    private int idLoaiSp;

    public ItemNews(int id,String hinhSpChinh, String tenSp, int giaSp, String hinh1, String hinh2,String hinh3,String hinh4, String moTaSp, int idLoaiSp) {
        this.id=id;
        this.hinhSpChinh = hinhSpChinh;
        this.tenSp = tenSp;
        this.giaSp = giaSp;
        this.hinh1 = hinh1;
        this.hinh2 = hinh2;
        this.hinh3 = hinh3;
        this.hinh4 = hinh4;
        this.moTaSp = moTaSp;
        this.idLoaiSp = idLoaiSp;
    }

    public String getHinh3() {
        return hinh3;
    }

    public void setHinh3(String hinh3) {
        this.hinh3 = hinh3;
    }

    public String getHinh4() {
        return hinh4;
    }

    public void setHinh4(String hinh4) {
        this.hinh4 = hinh4;
    }

    public String getHinhSpChinh() {
        return hinhSpChinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHinhSpChinh(String hinhSpChinh) {
        this.hinhSpChinh = hinhSpChinh;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(int giaSp) {
        this.giaSp = giaSp;
    }

    public String getHinh1() {
        return hinh1;
    }

    public void setHinh1(String hinh1) {
        this.hinh1 = hinh1;
    }

    public String getHinh2() {
        return hinh2;
    }

    public void setHinh2(String hinh2) {
        this.hinh2 = hinh2;
    }

    public String getMoTaSp() {
        return moTaSp;
    }

    public void setMoTaSp(String moTaSp) {
        this.moTaSp = moTaSp;
    }

    public int getIdLoaiSp() {
        return idLoaiSp;
    }

    public void setIdLoaiSp(int idLoaiSp) {
        this.idLoaiSp = idLoaiSp;
    }
}
