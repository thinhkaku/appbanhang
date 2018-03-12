package com.example.administrator.phanmemqlbhonline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.model.ItemHangDaMua;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 11/27/2017.
 */

public class Act_ChiTietHangDaMua extends AppCompatActivity {
    private TextView txtTenSP, txtGiaSp, txtSoLuongSp,txtMauSacSp, txtSizeSp,txtTongTienPhaiTra;
    private ImageView imgHinh;
    private Button btnDongY;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chititiet_hangdamua);
        initView();
        addView();
    }

    private void addView() {
        Intent intent =getIntent();
        final ItemHangDaMua itemHangDaMua= (ItemHangDaMua) intent.getSerializableExtra("hangdamua");
        Picasso.with(Act_ChiTietHangDaMua.this).load(itemHangDaMua.getHinh()).into(imgHinh);
        txtTenSP.setText(itemHangDaMua.getTenSP());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtGiaSp.setText("Giá: : "+decimalFormat.format(itemHangDaMua.getGiaSP())+" vnđ");
        txtSoLuongSp.setText("Số lượng: "+itemHangDaMua.getSoLuong());
        txtMauSacSp.setText("Màu sắc: "+itemHangDaMua.getMauSac());
        txtSizeSp.setText("Size: "+itemHangDaMua.getSize());
        txtTongTienPhaiTra.setText("Tổng tiền: "+decimalFormat.format(itemHangDaMua.getGiaSP()*itemHangDaMua.getSoLuong())+" vnđ");
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        txtTenSP= (TextView) findViewById(R.id.txtTenSpChiTietDaMua);
        txtGiaSp= (TextView) findViewById(R.id.txtGiaSpChiTietDaMua);
        txtSoLuongSp= (TextView) findViewById(R.id.txtSoLuongChiTietDaMua);
        txtMauSacSp= (TextView) findViewById(R.id.txtMauSacChiTietDaMua);
        txtSizeSp= (TextView) findViewById(R.id.txtSizeChiTietDaMua);
        txtTongTienPhaiTra= (TextView) findViewById(R.id.txtTongTienPhaiTra);
        imgHinh= (ImageView) findViewById(R.id.imgHinhChiTietDaMua);
        btnDongY= (Button) findViewById(R.id.btnDongYDaMuaHang);
    }
}
