package com.example.administrator.phanmemqlbhonline.activity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.phanmemqlbhonline.MainActivity;
import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.adapter.AdapterHangDaMua;
import com.example.administrator.phanmemqlbhonline.model.ItemHangDaMua;
import com.example.administrator.phanmemqlbhonline.sqlite.SQLiteHangDaMua;

import java.util.ArrayList;

/**
 * Created by Administrator on 11/11/2017.
 */

public class Act_HangDaMua extends AppCompatActivity {
    private ArrayList<ItemHangDaMua>arrHangDaMua=new ArrayList<>();
    private AdapterHangDaMua adapterHangDaMua;
    private GridView gvHangDaMua;
    private SQLiteHangDaMua sqLiteHangDaMua;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hang_da_mua);
        sqLiteHangDaMua= new SQLiteHangDaMua(this);
        readData();
        initActionBar();
        initView();
    }

    private void readData(){
        arrHangDaMua.clear();
        arrHangDaMua.addAll(sqLiteHangDaMua.getDataHangDaMua());
    }

    private void initActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initView() {
        gvHangDaMua= (GridView) findViewById(R.id.gvHangDaMua);
        adapterHangDaMua=new AdapterHangDaMua(Act_HangDaMua.this,arrHangDaMua);
        gvHangDaMua.setAdapter(adapterHangDaMua);
        gvHangDaMua.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemHangDaMua itemHangDaMua = arrHangDaMua.get(position);
                Intent intent =new Intent(Act_HangDaMua.this,Act_ChiTietHangDaMua.class);
                intent.putExtra("hangdamua",itemHangDaMua);
                startActivity(intent);
            }
        });
        gvHangDaMua.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ItemHangDaMua itemHangDaMua =arrHangDaMua.get(position);
                sqLiteHangDaMua.delete(itemHangDaMua.getId());
                Toast.makeText(Act_HangDaMua.this,"Xóa khỏi danh sách hàng đã mua thành công",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
                if(item.getItemId()==android.R.id.home){
            Intent intent = new Intent(Act_HangDaMua.this, MainActivity.class);
            setResult(50,intent);
            finish();
            overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Act_HangDaMua.this, MainActivity.class);
        setResult(50,intent);
        finish();
        overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
    }
}
