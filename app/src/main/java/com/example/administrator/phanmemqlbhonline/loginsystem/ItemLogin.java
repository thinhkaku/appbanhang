package com.example.administrator.phanmemqlbhonline.loginsystem;

/**
 * Created by Administrator on 10/16/2017.
 */

public class ItemLogin {
    private int id;
    private String userNane;
    private String passWord;

    public ItemLogin() {
    }

    public ItemLogin(int id, String userNane, String passWord) {
        this.id = id;
        this.userNane = userNane;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserNane() {
        return userNane;
    }

    public void setUserNane(String userNane) {
        this.userNane = userNane;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
