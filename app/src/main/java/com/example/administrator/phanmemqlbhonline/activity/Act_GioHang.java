package com.example.administrator.phanmemqlbhonline.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.phanmemqlbhonline.MainActivity;
import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.adapter.AdapterNews;
import com.example.administrator.phanmemqlbhonline.model.ItemNews;
import com.example.administrator.phanmemqlbhonline.sqlite.SQLiteData;

import java.sql.SQLData;
import java.util.ArrayList;

/**
 * Created by Administrator on 11/1/2017.
 */

public class Act_GioHang extends AppCompatActivity {
    private ArrayList<ItemNews>arrHang=new ArrayList<>();
    private GridView gridViewHang;
    private AdapterNews adapterNews;
    private SQLiteData sqlData;
    private boolean ktClick=true;
    private Dialog dialog,dialogXoaKhoiGioHang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohangdaluu);
        //initToolBar();
        sqlData= new SQLiteData(this);
        readData();
        initView();
        readData();
    }


    private void readData() {
        arrHang.clear();
        arrHang.addAll(sqlData.getData());
    }
    private void initDialogHoiXoaKhoiGioHang(final int position){
        dialogXoaKhoiGioHang=new Dialog(this);
        dialogXoaKhoiGioHang.setContentView(R.layout.diaglog_xoakhoigiohang);
        dialogXoaKhoiGioHang.setCancelable(true);
        dialogXoaKhoiGioHang.setCanceledOnTouchOutside(true);
        Button btnCo= (Button) dialogXoaKhoiGioHang.findViewById(R.id.btnXoakhoiGioHang);
        Button btnKhong= (Button) dialogXoaKhoiGioHang.findViewById(R.id.btnKhongXoaKhoiGioHang);
        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktClick=true;
                ItemNews itemNews = arrHang.get(position);
                sqlData.delete(itemNews.getId());
                initDiaLogXoaHang();
                dialog.show();
                dialogXoaKhoiGioHang.dismiss();
            }
        });
        btnKhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktClick=true;
                dialogXoaKhoiGioHang.dismiss();
            }
        });
    }

    private void initDiaLogXoaHang(){
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.layout_xoa_thanh_cong);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Button button= (Button) dialog.findViewById(R.id.btnDongYY);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
                adapterNews.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }
    private void initView() {
        gridViewHang= (GridView) findViewById(R.id.gvGioHang);
        adapterNews=new AdapterNews(Act_GioHang.this,arrHang);
        gridViewHang.setAdapter(adapterNews);

        gridViewHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ktClick=false;
                initDialogHoiXoaKhoiGioHang(position);
                dialogXoaKhoiGioHang.show();
                return false;

            }
        });
        gridViewHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (ktClick==true){
                    ItemNews itemNews =arrHang.get(position);
                    Intent intent = new Intent(Act_GioHang.this,Act_ChiTietSP.class);
                    intent.putExtra("id",itemNews.getId());
                    intent.putExtra("tensp",itemNews.getTenSp());
                    intent.putExtra("giasp",itemNews.getGiaSp());
                    intent.putExtra("mota",itemNews.getMoTaSp());
                    intent.putExtra("hinhchinh",itemNews.getHinhSpChinh());
                    intent.putExtra("hinh1",itemNews.getHinh1());
                    intent.putExtra("hinh2",itemNews.getHinh2());
                    intent.putExtra("hinh3",itemNews.getHinh3());
                    intent.putExtra("hinh4",itemNews.getHinh4());
                    intent.putExtra("loaisp",itemNews.getIdLoaiSp());
                    startActivity(intent);
                    overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
                }

            }
        });

    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Act_GioHang.this, MainActivity.class);
        setResult(50,intent);
        finish();
        overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
    }
}
