package com.example.administrator.phanmemqlbhonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.phanmemqlbhonline.MainActivity;
import com.example.administrator.phanmemqlbhonline.R;

/**
 * Created by Administrator on 10/29/2017.
 */

public class Act_tuy_chon_cho_nguoidung extends AppCompatActivity implements View.OnClickListener {
    private Button btnKhachHang, btnCuaHang, btnShipper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tuychonnguoidung);
        initView();
    }

    private void initView() {
        btnCuaHang= (Button) findViewById(R.id.btnAdmin);
        btnKhachHang= (Button) findViewById(R.id.btnKhachHang);
        btnShipper= (Button) findViewById(R.id.btnShipper);
        btnShipper.setOnClickListener(this);
        btnKhachHang.setOnClickListener(this);
        btnCuaHang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdmin:
                Intent intent =new Intent(Act_tuy_chon_cho_nguoidung.this,LoginSystem.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
                break;
            case R.id.btnKhachHang:
                Intent intent1=new Intent(Act_tuy_chon_cho_nguoidung.this,MainActivity.class);
                startActivity(intent1);
                finish();
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
                break;
            case R.id.btnShipper:
                Intent intent2=new Intent(Act_tuy_chon_cho_nguoidung.this,Act_Shipper.class);
                startActivity(intent2);
                finish();
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
                break;
        }
    }
}
