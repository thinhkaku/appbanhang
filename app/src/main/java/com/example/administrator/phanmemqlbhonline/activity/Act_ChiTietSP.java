package com.example.administrator.phanmemqlbhonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.administrator.phanmemqlbhonline.MainActivity;
import com.example.administrator.phanmemqlbhonline.adapter.AdapterChiTietSP;
import com.example.administrator.phanmemqlbhonline.model.ItemNews;
import com.example.administrator.phanmemqlbhonline.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 10/29/2017.
 */

public class Act_ChiTietSP extends AppCompatActivity {
    private ArrayList<ItemNews>dsChitieSP;
    private AdapterChiTietSP adapterChiTietSP;
    private ListView lvChiTietSP;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chi_tiet_sp);
        //initNavirationOpen();
        initView();
    }
    private void initNavirationOpen() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    private void initView() {
        lvChiTietSP= (ListView) findViewById(R.id.lvChiTietSp);
        dsChitieSP = new ArrayList<>();
        Intent intent = getIntent();
        int id=intent.getIntExtra("id",-1);
        String tensp=intent.getStringExtra("tensp");
        int gia=intent.getIntExtra("giasp",-1);
        String mota=intent.getStringExtra("mota");
        String hinhchinh=intent.getStringExtra("hinhchinh");
        String hinh1=intent.getStringExtra("hinh1");
        String hinh2=intent.getStringExtra("hinh2");
        String hinh3=intent.getStringExtra("hinh3");
        String hinh4=intent.getStringExtra("hinh4");
        int loaisp=intent.getIntExtra("loaisp",-1);
        ItemNews itemChat = new ItemNews(id,hinhchinh,tensp,gia,hinh1,hinh2,hinh3,hinh4,mota,loaisp);
        dsChitieSP.add(itemChat);
        adapterChiTietSP = new AdapterChiTietSP(Act_ChiTietSP.this,dsChitieSP);
        lvChiTietSP.setAdapter(adapterChiTietSP);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId()==android.R.id.home){
//            Intent intent = new Intent(Act_ChiTietSP.this, MainActivity.class);
//            setResult(50,intent);
//            finish();
//            overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Act_ChiTietSP.this, MainActivity.class);
        setResult(50,intent);
        finish();
        overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
    }
}
