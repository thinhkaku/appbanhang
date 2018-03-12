package com.example.administrator.phanmemqlbhonline.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.adapter.AdapterDanhSachKhachHang;
import com.example.administrator.phanmemqlbhonline.config.LoginConfig;
import com.example.administrator.phanmemqlbhonline.loginsystem.BaseActivity;
import com.example.administrator.phanmemqlbhonline.model.ItemNews;
import com.example.administrator.phanmemqlbhonline.model.Item_DanhSachHang;
import com.example.administrator.phanmemqlbhonline.sqlite.SQLiteDonHangHuy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Act_QuanLyCuaHang extends BaseActivity implements View.OnClickListener {
    private ArrayList<Item_DanhSachHang>arrayListKhachHang=new ArrayList<>();
    private AdapterDanhSachKhachHang adapterDanhSachKhachHang;
    private ListView lvDanhSach;
    private Dialog dialogTuyChon;
    private SQLiteDonHangHuy sqLiteDonHangHuy;
    private Dialog dialog;
    private DrawerLayout drawer;
    private LinearLayout linearDangXuat,linearChuaXacNhan, linerDaXacNhan, linearBiHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        sqLiteDonHangHuy = new SQLiteDonHangHuy(this);
        layThongTinKhachHang(LoginConfig.layThongTinKhachHang());
        initView();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_act__quan_ly_cua_hang;
    }

    @Override
    protected void initView() {
        lvDanhSach = (ListView) findViewById(R.id.lvDanhSach);
        linearBiHuy= (LinearLayout) findViewById(R.id.linearBihuy);
        linearChuaXacNhan= (LinearLayout) findViewById(R.id.linearchuaxacnhan);
        linearDangXuat= (LinearLayout) findViewById(R.id.linearLogout1);
        linerDaXacNhan= (LinearLayout) findViewById(R.id.linearDaxacnhan);
        adapterDanhSachKhachHang = new AdapterDanhSachKhachHang(Act_QuanLyCuaHang.this,arrayListKhachHang);
        lvDanhSach.setAdapter(adapterDanhSachKhachHang);
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item_DanhSachHang item_danhSachHang = arrayListKhachHang.get(position);
                Intent intent = new Intent(Act_QuanLyCuaHang.this,Act_XacNhanDonHang.class);
                intent.putExtra("id",item_danhSachHang.getId());
                intent.putExtra("hinh",item_danhSachHang.getHinh());
                intent.putExtra("tensp",item_danhSachHang.getTenSp());
                intent.putExtra("giasp",item_danhSachHang.getGiasp());
                intent.putExtra("soluong",item_danhSachHang.getSoluong());
                intent.putExtra("size",item_danhSachHang.getSizeSp());
                intent.putExtra("mausac",item_danhSachHang.getMauSac());
                intent.putExtra("tenkh",item_danhSachHang.getTenKhachHang());
                intent.putExtra("sdt",item_danhSachHang.getsDTKhachHang());
                intent.putExtra("diachi",item_danhSachHang.getDiaChiKhachHang());
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
            }
        });
        linerDaXacNhan.setOnClickListener(this);
        linearDangXuat.setOnClickListener(this);
        linearChuaXacNhan.setOnClickListener(this);
        linearBiHuy.setOnClickListener(this);
    }
    private void initDialogTuyChon(int position){
        dialogTuyChon = new Dialog(this);
        dialogTuyChon.setCanceledOnTouchOutside(true);
        dialogTuyChon.setCancelable(true);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            initDialogQuitApp();
            dialog.dismiss();
        }
    }

    private void initDialogQuitApp(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_quit_app);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        Button btnYes = (Button) dialog.findViewById(R.id.btnCo);
        Button btnNo = (Button) dialog.findViewById(R.id.btnKhong);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onSucess(String result) {
        arrayListKhachHang.clear();
        try {
            JSONArray jsonArray = new JSONArray(result);
            //JSONObject jsonObject = new JSONObject(result);
            for (int i =0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id=jsonObject.getInt("id");
                String  hinh=jsonObject.getString("hinh");
                String tenSp = jsonObject.getString("tensp");
                int giasp = jsonObject.getInt("giasp");
                int  soluong=jsonObject.getInt("soluong");
                String mausac=jsonObject.getString("mausac");
                String size= jsonObject.getString("size");
                String tenKhachHang = jsonObject.getString("tenkhachhang");
                String sDt = jsonObject.getString("sdt");
                String diaChi=jsonObject.getString("diachi");
                Item_DanhSachHang item_danhSachHang = new Item_DanhSachHang(id,hinh,tenSp,giasp,soluong,mausac,size,tenKhachHang,sDt,diaChi);
                arrayListKhachHang.add(item_danhSachHang);
                adapterDanhSachKhachHang.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linearLogout1:
                drawer.closeDrawers();
                Intent intent = new Intent(Act_QuanLyCuaHang.this,Act_tuy_chon_cho_nguoidung.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
                break;
            case R.id.linearBihuy:
                arrayListKhachHang.clear();
                arrayListKhachHang.addAll(sqLiteDonHangHuy.getDataHangBiHuy());
                adapterDanhSachKhachHang.notifyDataSetChanged();
                drawer.closeDrawers();
                break;
            case R.id.linearDaxacnhan:
                arrayListKhachHang.clear();
                arrayListKhachHang.addAll(sqLiteDonHangHuy.getDataHangDaXacNhan());
                adapterDanhSachKhachHang.notifyDataSetChanged();
                drawer.closeDrawers();
                break;
            case R.id.linearchuaxacnhan:
                adapterDanhSachKhachHang.clear();
                layThongTinKhachHang(LoginConfig.layThongTinKhachHang());
                adapterDanhSachKhachHang.notifyDataSetChanged();
                drawer.closeDrawers();
                break;
        }
    }

}
