package com.example.administrator.phanmemqlbhonline.loginsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.phanmemqlbhonline.config.LoginConfig;
import com.example.administrator.phanmemqlbhonline.interfaceloginsystem.LoginSystemInterface;

/**
 * Created by Administrator on 10/16/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements LoginSystemInterface {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initView();
    }
    protected abstract int getLayoutID();
    protected abstract void initView();
    @Override
    public void onEror() {
        Toast.makeText(this,"Thất bại, kiểm tra lại đường truyền",Toast.LENGTH_SHORT).show();
    }


    protected void loginAPI(String api){
        LoginAsync loginAsync = new LoginAsync(this,BaseActivity.this);
        loginAsync.execute(api);
    }
    protected void getSP(String api){
        LoginAsync loginAsync = new LoginAsync(this,BaseActivity.this);
        loginAsync.execute(api);
    }
    protected void guiThongtiN(String aip){
        LoginAsync loginAsync = new LoginAsync(this,BaseActivity.this);
        loginAsync.execute(aip);
    }

    protected void layThongTinKhachHang(String api){
        LoginAsync loginAsync = new LoginAsync(this,BaseActivity.this);
        loginAsync.execute(api);
    }
    protected void xoaDonHang(String api){
        LoginAsync loginAsync=new LoginAsync(this,BaseActivity.this);
        loginAsync.execute(api);
    }
    protected void guiShipper(String api){
        LoginAsync loginAsync=new LoginAsync(this,BaseActivity.this);
        loginAsync.execute(api);
    }
    protected void laydulieuShipper(String api){
        LoginAsync loginAsync=new LoginAsync(this,BaseActivity.this);
        loginAsync.execute(api);
    }
}
