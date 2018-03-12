package com.example.administrator.phanmemqlbhonline;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.administrator.phanmemqlbhonline.activity.Act_ChiTietSP;
import com.example.administrator.phanmemqlbhonline.activity.Act_GioHang;
import com.example.administrator.phanmemqlbhonline.activity.Act_HangDaMua;
import com.example.administrator.phanmemqlbhonline.activity.Act_tuy_chon_cho_nguoidung;
import com.example.administrator.phanmemqlbhonline.activity.LoginSystem;
import com.example.administrator.phanmemqlbhonline.adapter.AdapterNews;
import com.example.administrator.phanmemqlbhonline.adapter.Adapter_DanhSachHang;
import com.example.administrator.phanmemqlbhonline.config.LoginConfig;
import com.example.administrator.phanmemqlbhonline.loginsystem.BaseActivity;
import com.example.administrator.phanmemqlbhonline.model.ItemNews;
import com.example.administrator.phanmemqlbhonline.sqlite.SQLiteData;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends BaseActivity
        implements  View.OnClickListener,AbsListView.OnScrollListener {
    private int spinner=1;
    private Spinner spLoaiSp;
    private ArrayList<String>dsDam;
    private ArrayAdapter<String>adapterDam;
    private ImageView btnHome,btnGioHang;
    private TextView txtLogin;
    private ListView listView;
    private Dialog dialog;
    private ArrayList<ItemNews>dsarrr = new ArrayList<>();
    private View loadView;
    private GridView gvDanhsach;
    private boolean ktLoad=false;
    private Adapter_DanhSachHang adapter_danhSachHang;
    private ViewFlipper viewFlipper;
    private DrawerLayout drawerLayout;
    private LinearLayout linearDam, linearAo,linearQuan,linearDangxuat,linearAoKhoac,linearDoLot,linearTrangPhuc,linearHangDaMua,linearThongTinCuaHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutID());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
       drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        getSP(LoginConfig.getSp());
        initView();
        viewFilpper();
        addEvent();
    }
    private void viewFilpper() {
        ArrayList<String>arrHinh= new ArrayList<>();
        arrHinh.add("https://media3.scdn.vn/img2/2017/11_7/dam-len-suong-phoi-tay-ren-cao-cap-1m4G3-brCNRh_simg_d0daf0_800x1200_max.jpg");
        arrHinh.add("https://media3.scdn.vn/img2/2017/7_4/ao-voan-hoa-theu-cao-cap-1m4G3-fuAyp3_simg_d0daf0_800x1200_max.jpg");
        arrHinh.add("https://media3.scdn.vn/img2/2017/10_29/dam-du-tiec-sang-trong-d4156-1m4G3-AxSKkx_simg_d0daf0_800x1200_max.jpg");
        arrHinh.add("https://media3.scdn.vn/img2/2017/11_2/dam-om-len-sang-trong-hang-nhap-dl095-1m4G3-11qVP9_simg_d0daf0_800x1200_max.jpg");
        arrHinh.add("https://media3.scdn.vn/img2/2017/9_14/set-ao-vay-len-xinh-xan-it496-1m4G3-bpJo36_simg_d0daf0_800x1200_max.jpg");
        for(int i=0;i<arrHinh.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(arrHinh.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animationRight = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_right);
        Animation animationLeft = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_left);
        viewFlipper.setInAnimation(animationRight);
        viewFlipper.setOutAnimation(animationLeft);
        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }
    private void addEvent() {
        linearAo.setOnClickListener(this);
        linearAoKhoac.setOnClickListener(this);
        linearDam.setOnClickListener(this);
        linearDangxuat.setOnClickListener(this);
        linearQuan.setOnClickListener(this);
        linearDoLot.setOnClickListener(this);
        linearTrangPhuc.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnGioHang.setOnClickListener(this);
        linearHangDaMua.setOnClickListener(this);
        linearThongTinCuaHang.setOnClickListener(this);
        spLoaiSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chonTatCaSanPham(position);
                chonSanPhamDamVay(position);
                chonSanPhamAo(position);
                chonSanPhamDoLot(position);
                chonSanPhamTrangPhuc(position);
                chonSanPhamQuan(position);
                chonSanPhamAoKhoac(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void chonTatCaSanPham(int position){
        if (position==0&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.getSp());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==1&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get1());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==2&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get2());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==3&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get3());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==4&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get4());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==5&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get5());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==6&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get6());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==7&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get7());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==8&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get8());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==9&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get9());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==10&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get10());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==11&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get11());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==12&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get12());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==13&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get13());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==14&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get14());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==15&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get15());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==16&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get16());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==17&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get17());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==18&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get18());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==19&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get19());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==20&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get20());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==21&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get21());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==22&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get22());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==23&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get23());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==24&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get24());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==25&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get25());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==26&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get26());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==27&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get27());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==28&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get28());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==29&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get29());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==30&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get30());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==31&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get31());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==32&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get32());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==33&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get33());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==34&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get34());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==35&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get35());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==36&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get36());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==37&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get37());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==38&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get38());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==39&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get39());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==40&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get40());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==41&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get41());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==42&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get42());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==43&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get43());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==44&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get44());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==45&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get45());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==46&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get46());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==47&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get47());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==48&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get48());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==49&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get49());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==50&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get50());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==51&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get51());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==52&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get52());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==53&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get53());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==54&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get54());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==55&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get55());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==56&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get56());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==57&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get57());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==58&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get58());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==59&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get59());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==60&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get60());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==61&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get61());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==62&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get62());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==63&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get63());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==64&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get64());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==65&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get65());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==66&&spinner==1){
            spinner=1;
            dsarrr.clear();
            getSP(LoginConfig.get66());
            adapter_danhSachHang.notifyDataSetChanged();
        }
    }
    private void chonSanPhamDamVay(int position){
        if (position==0&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get1());
            adapter_danhSachHang.notifyDataSetChanged();
        }if (position==1&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get2());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==2&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get3());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==3&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get4());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==4&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get5());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==5&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get6());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==6&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get7());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==7&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get8());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==8&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get9());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==9&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get10());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==10&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get11());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==11&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get12());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==12&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get13());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==13&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get14());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==14&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get15());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==15&&spinner==2){
            spinner=2;
            dsarrr.clear();
            getSP(LoginConfig.get16());
            adapter_danhSachHang.notifyDataSetChanged();
        }
    }
    private void chonSanPhamAo(int position){
        if (position==0&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get17());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==1&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get18());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==2&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get19());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==3&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get20());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==4&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get21());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==5&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get22());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==6&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get23());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==7&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get24());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==8&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get25());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==9&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get26());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==10&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get27());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==11&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get28());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==12&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get29());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==13&&spinner==3){
            spinner=3;
            dsarrr.clear();
            getSP(LoginConfig.get30());
            adapter_danhSachHang.notifyDataSetChanged();
        }
    }
    private void chonSanPhamQuan(int position){
        if (position==0&&spinner==4){
            spinner=4;
            dsarrr.clear();
            getSP(LoginConfig.get41());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==1&&spinner==4){
            spinner=4;
            dsarrr.clear();
            getSP(LoginConfig.get42());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==2&&spinner==4){
            spinner=4;
            dsarrr.clear();
            getSP(LoginConfig.get43());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==3&&spinner==4){
            spinner=4;
            dsarrr.clear();
            getSP(LoginConfig.get44());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==4&&spinner==4){
            spinner=4;
            dsarrr.clear();
            getSP(LoginConfig.get45());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==5&&spinner==4){
            spinner=4;
            dsarrr.clear();
            getSP(LoginConfig.get46());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==6&&spinner==4){
            spinner=4;
            dsarrr.clear();
            getSP(LoginConfig.get47());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==7&&spinner==4){
            spinner=4;
            dsarrr.clear();
            getSP(LoginConfig.get48());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==8&&spinner==4){
            spinner=4;
            dsarrr.clear();
            getSP(LoginConfig.get49());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==9&&spinner==4){
            spinner=4;
            dsarrr.clear();
            getSP(LoginConfig.get50());
            adapter_danhSachHang.notifyDataSetChanged();
        }
    }
    private void chonSanPhamAoKhoac(int position){
        if (position==0&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get51());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==1&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get52());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==2&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get53());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==3&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get54());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==4&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get55());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==5&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get56());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==6&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get57());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==7&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get58());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==8&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get59());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==9&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get60());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==10&&spinner==5){
            spinner=5;
            dsarrr.clear();
            getSP(LoginConfig.get61());
            adapter_danhSachHang.notifyDataSetChanged();
        }
    }
    private void chonSanPhamTrangPhuc(int position){
        if (position==0&&spinner==6){
            spinner=6;
            dsarrr.clear();
            getSP(LoginConfig.get31());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==1&&spinner==6){
            spinner=6;
            dsarrr.clear();
            getSP(LoginConfig.get32());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==2&&spinner==6){
            spinner=6;
            dsarrr.clear();
            getSP(LoginConfig.get33());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==3&&spinner==6){
            spinner=6;
            dsarrr.clear();
            getSP(LoginConfig.get34());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==4&&spinner==6){
            spinner=6;
            dsarrr.clear();
            getSP(LoginConfig.get35());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==5&&spinner==6){
            spinner=6;
            dsarrr.clear();
            getSP(LoginConfig.get36());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==6&&spinner==6){
            spinner=6;
            dsarrr.clear();
            getSP(LoginConfig.get37());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==7&&spinner==6){
            spinner=6;
            dsarrr.clear();
            getSP(LoginConfig.get38());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==8&&spinner==6){
            spinner=6;
            dsarrr.clear();
            getSP(LoginConfig.get39());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==9&&spinner==6){
            spinner=6;
            dsarrr.clear();
            getSP(LoginConfig.get40());
            adapter_danhSachHang.notifyDataSetChanged();
        }
    }
    private void chonSanPhamDoLot(int position){
        if (position==0&&spinner==7){
            spinner=7;
            dsarrr.clear();
            getSP(LoginConfig.get62());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==1&&spinner==7){
            spinner=7;
            dsarrr.clear();
            getSP(LoginConfig.get63());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==2&&spinner==7){
            spinner=7;
            dsarrr.clear();
            getSP(LoginConfig.get64());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==3&&spinner==7){
            spinner=7;
            dsarrr.clear();
            getSP(LoginConfig.get65());
            adapter_danhSachHang.notifyDataSetChanged();
        }
        if (position==4&&spinner==7){
            spinner=7;
            dsarrr.clear();
            getSP(LoginConfig.get66());
            adapter_danhSachHang.notifyDataSetChanged();
        }
    }
    private void addSpinner(){
        dsDam.add("Tất cả sản phẩm");
        dsDam.add("Đầm ren");dsDam.add("Đầm xòe");dsDam.add("Đầm suông");
        dsDam.add("Đầm ôm");dsDam.add("Đầm maxi");dsDam.add("Đầm dạ hội");dsDam.add("Đầm công sở");dsDam.add("Đầm thun");
        dsDam.add("Đầm trễ vai");dsDam.add("Đầm voan chiffon");dsDam.add("Đầm xếp ly");dsDam.add("Đầm peplum");dsDam.add("Đầm đuôi cá");
        dsDam.add("Đầm vest");dsDam.add("Đầm denim");dsDam.add("Đầm len");

        dsDam.add("Áo sơ mi nữ");dsDam.add("Áo trễ vai");dsDam.add("Áo kiểu");
        dsDam.add("Áo đôi");dsDam.add("Áo ren");dsDam.add("Áo thun có tay");dsDam.add("Áo thun không tay");dsDam.add("Áo hai dây, áo quây");dsDam.add("Áo dài");
        dsDam.add("Áo hoodie");dsDam.add("Áo peplum");dsDam.add("Áo denim");dsDam.add("Áo len");dsDam.add("Áo voan, chiffon");

        dsDam.add("Áo và chân váy");dsDam.add("Áo và quần dài");dsDam.add("Áo và quần ngắn");dsDam.add("Áo và quần lửng");dsDam.add("Áo và quần culottes");
        dsDam.add("Áo và quần giả váy");dsDam.add("Jumpsuit");dsDam.add("Bộ vest chân váy");dsDam.add("Bộ vest quần ngắn");dsDam.add("Bộ vest quần dài");

        dsDam.add("Quần short nữ"); dsDam.add("Quần jean"); dsDam.add("Quần tây"); dsDam.add("Quần kaki"); dsDam.add("Quần baggy"); dsDam.add("Quần ống rộng"); dsDam.add("Quần giả váy"); dsDam.add("Quần legging"); dsDam.add("Quần yếm"); dsDam.add("Quần lửng nữ");

        dsDam.add("Áo Khoác vest, blazer");dsDam.add("Áo khoác chống nắng");dsDam.add("Áo khoác cách điệu");dsDam.add("Áo ghi lê");dsDam.add("Áo khoác len, cardigan");dsDam.add("Áo khoác da, mãng tô áo choàng");
        dsDam.add("Áo khoác phao");dsDam.add("Áo khoác da");dsDam.add("Áo khoác đôi");dsDam.add("Áo khoác jean");dsDam.add("Áo khoác lửng");

        dsDam.add("Áo ngực");dsDam.add("Quần lót nữ");dsDam.add("Phụ kiện áo ngực");dsDam.add("Đồ lót bộ");dsDam.add("Đồ lót định hình");
    }
    public void initView() {
        btnHome= (ImageView) findViewById(R.id.btnHome);
        btnGioHang= (ImageView) findViewById(R.id.btnGioHang);
        spLoaiSp= (Spinner) findViewById(R.id.spLoaiSp);
        dsDam= new ArrayList<>();
        addSpinner();
        adapterDam= new ArrayAdapter<String>(MainActivity.this,R.layout.text,dsDam);
        spLoaiSp.setAdapter(adapterDam);
        adapterDam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        linearDoLot = (LinearLayout) findViewById(R.id.linearDoLot);
        linearDangxuat = (LinearLayout) findViewById(R.id.linearLogout);
        linearTrangPhuc = (LinearLayout) findViewById(R.id.linearTrangPhuc);
        linearQuan = (LinearLayout) findViewById(R.id.linearQuan);
        linearDam = (LinearLayout) findViewById(R.id.linearDamVay);
        linearAoKhoac = (LinearLayout) findViewById(R.id.linearAoKhoac);
        linearAo = (LinearLayout) findViewById(R.id.linearAo);
        linearHangDaMua = (LinearLayout) findViewById(R.id.linearHangDaMua);
        linearThongTinCuaHang = (LinearLayout) findViewById(R.id.linearThongTinCuaHang);

        gvDanhsach = (GridView) findViewById(R.id.gvDanhsach);
        LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loadView=inflater.inflate(R.layout.load_view,null);
        adapter_danhSachHang=new Adapter_DanhSachHang(dsarrr,MainActivity.this);
        gvDanhsach.setAdapter(adapter_danhSachHang);
        gvDanhsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemNews itemNews =dsarrr.get(position);
                Intent intent = new Intent(MainActivity.this,Act_ChiTietSP.class);
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
        });
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            initDialogQuitApp();
            dialog.show();
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
    public void onClick(View v) {
        int id= v.getId();
        switch (id){
            case R.id.linearThongTinCuaHang:
                drawerLayout.closeDrawers();
                break;
            case R.id.linearHangDaMua:
                drawerLayout.closeDrawers();
                Intent intent3=new Intent(MainActivity.this, Act_HangDaMua.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);

                break;
            case R.id.btnGioHang:
                Intent inten=new Intent(MainActivity.this, Act_GioHang.class);
                startActivity(inten);
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
                break;
            case R.id.btnHome:
                spinner=1;
                dsDam.clear();
                dsarrr.clear();
                addSpinner();
                adapterDam.notifyDataSetChanged();
                getSP(LoginConfig.getSp());
                adapter_danhSachHang.notifyDataSetChanged();
                spLoaiSp.setSelection(0);
                break;
            case R.id.linearAo:
                spinner=3;
                getSP(LoginConfig.getAo());
                dsDam.clear();
                dsarrr.clear();
                dsDam.add("Áo sơ mi nữ");dsDam.add("Áo trễ vai");dsDam.add("Áo kiểu");
                dsDam.add("Áo đôi");dsDam.add("Áo ren");dsDam.add("Áo thun có tay");dsDam.add("Áo thun không tay");dsDam.add("Áo hai dây, áo quây");dsDam.add("Áo dài");
                dsDam.add("Áo hoodie");dsDam.add("Áo peplum");dsDam.add("Áo denim");dsDam.add("Áo len");dsDam.add("Áo voan, chiffon");
                adapterDam.notifyDataSetChanged();
                adapter_danhSachHang.notifyDataSetChanged();
                drawerLayout.closeDrawers();
                spLoaiSp.setSelection(0);
                break;
            case R.id.linearAoKhoac:
                spinner=5;
                dsarrr.clear();
                getSP(LoginConfig.getAoKhoac());
                dsDam.clear();
                dsDam.add("Áo Khoác vest, blazer");dsDam.add("Áo khoác chống nắng");dsDam.add("Áo cách điệu");dsDam.add("Áo ghi lê");dsDam.add("Áo khoác len, cardigan");dsDam.add("Áo khoác da, mãng tô áo choàng");
                dsDam.add("Áo khoác phao");dsDam.add("Áo khoác da");dsDam.add("Áo khoác đôi");dsDam.add("Áo khoác jean");dsDam.add("Áo khoác lửng");
                adapterDam.notifyDataSetChanged();
                adapter_danhSachHang.notifyDataSetChanged();
                drawerLayout.closeDrawers();
                spLoaiSp.setSelection(0);
                break;
            case R.id.linearDamVay:
                spinner=2;
                dsarrr.clear();
                getSP(LoginConfig.getDamVay());
                dsDam.clear();
                dsDam.add("Đầm ren");dsDam.add("Đầm xòe");dsDam.add("Đầm suông");
                dsDam.add("Đầm ôm");dsDam.add("Đầm maxi");dsDam.add("Đầm dạ hội");dsDam.add("Đầm công sở");dsDam.add("Đầm thun");
                dsDam.add("Đầm trễ vai");dsDam.add("Đầm voan chiffon");dsDam.add("Đầm xếp ly");dsDam.add("Đầm peplum");dsDam.add("Đầm đuôi cá");
                dsDam.add("Đầm vest");dsDam.add("Đầm denim");dsDam.add("Đầm len");
                adapterDam.notifyDataSetChanged();
                adapter_danhSachHang.notifyDataSetChanged();
                drawerLayout.closeDrawers();
                spLoaiSp.setSelection(0);
                break;
            case R.id.linearLogout:
                drawerLayout.closeDrawers();
                Intent intent = new Intent(MainActivity.this,Act_tuy_chon_cho_nguoidung.class);
                startActivity(intent);
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
                break;
            case R.id.linearDoLot:
                spinner=7;
                dsarrr.clear();
                getSP(LoginConfig.getDoLot());
                dsDam.clear();
                dsDam.add("Áo ngực");dsDam.add("Quần lót nữ");dsDam.add("Phụ kiện áo ngực");dsDam.add("Đồ lót bộ");dsDam.add("Đồ lót định hình");
                adapterDam.notifyDataSetChanged();
                adapter_danhSachHang.notifyDataSetChanged();
                drawerLayout.closeDrawers();
                spLoaiSp.setSelection(0);
                break;
            case R.id.linearTrangPhuc:
                spinner=6;
                dsarrr.clear();
                getSP(LoginConfig.getTrangPhuc());
                dsDam.clear();
                dsDam.add("Áo và chân váy");dsDam.add("Áo và quần dài");dsDam.add("Áo và quần ngắn");dsDam.add("Áo và quần lửng");dsDam.add("Áo và quần culottes");
                dsDam.add("Áo và quần giả váy");dsDam.add("Jumpsuit");dsDam.add("Bộ vest chân váy");dsDam.add("Bộ vest quần ngắn");dsDam.add("Bộ vest quần dài");
                adapterDam.notifyDataSetChanged();
                adapter_danhSachHang.notifyDataSetChanged();
                drawerLayout.closeDrawers();
                spLoaiSp.setSelection(0);
                break;
            case R.id.linearQuan:
                spinner=4;
                dsarrr.clear();
                getSP(LoginConfig.getQuan());
                dsDam.clear();
                dsDam.add("Quần short nữ"); dsDam.add("Quần jean"); dsDam.add("Quần tây"); dsDam.add("Quần kaki"); dsDam.add("Quần baggy"); dsDam.add("Quần ống rộng"); dsDam.add("Quần giả váy"); dsDam.add("Quần legging"); dsDam.add("Quần yếm"); dsDam.add("Quần lửng nữ");
                adapterDam.notifyDataSetChanged();
                adapter_danhSachHang.notifyDataSetChanged();
                drawerLayout.closeDrawers();
                spLoaiSp.setSelection(0);
                break;
        }
    }



    @Override
    public void onSucess(String result) {
        dsarrr.clear();
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i =0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String tenSp = jsonObject.getString("tensp");
                int giasp = jsonObject.getInt("giasp");
                String hinh1 = jsonObject.getString("ha1");
                String hinh2 = jsonObject.getString("ha2");
                String hinh3 = jsonObject.getString("ha3");
                String hinh4 = jsonObject.getString("ha4");
                String hinh5 = jsonObject.getString("ha5");
                String mota = jsonObject.getString("motasp");
                int loaisp = jsonObject.getInt("idloaisp");
                ItemNews itemChat = new ItemNews(id,hinh1,tenSp,giasp,hinh2,hinh3,hinh4,hinh5,mota,loaisp);
                dsarrr.add(itemChat);
                adapter_danhSachHang.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}



