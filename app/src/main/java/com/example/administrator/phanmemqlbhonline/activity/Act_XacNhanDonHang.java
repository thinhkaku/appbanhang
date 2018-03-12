package com.example.administrator.phanmemqlbhonline.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.config.LoginConfig;
import com.example.administrator.phanmemqlbhonline.loginsystem.BaseActivity;
import com.example.administrator.phanmemqlbhonline.model.Item_DanhSachHang;
import com.example.administrator.phanmemqlbhonline.sqlite.SQLiteDonHangHuy;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;


/**
 * Created by Administrator on 11/2/2017.
 */

public class Act_XacNhanDonHang extends BaseActivity implements View.OnClickListener {
    private TextView txtTensp, txtGiaSP, txtSoLuong, txtMauSac, txtSize, txtTenKhachHang, txtSDT, txtDiaChi, txtTienThanhToan;
    private ImageView imgHinh;
    private String sdt;
    private Dialog dialogDonHangHuy, dialogDaXacNhan;
    private Item_DanhSachHang item_danhSachHang;
    private SQLiteDonHangHuy sqLiteDonHangHuy;
    private Button btnGoi, btnGuiShipper, btnHuyDonHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        sqLiteDonHangHuy=new SQLiteDonHangHuy(this);
        initView();
        addEvent();
    }

    private void addEvent() {
        Intent intent = getIntent();
        int giasp = intent.getIntExtra("giasp", -1);
        int sl = intent.getIntExtra("soluong", -1);
        int tien = giasp * sl;
        int id=intent.getIntExtra("id",-1);
        String hinh=intent.getStringExtra("hinh");
        String tensp=intent.getStringExtra("tensp");
        String mausac=intent.getStringExtra("mausac");
        String size=intent.getStringExtra("size");
        String tenkh=intent.getStringExtra("tenkh");
        String diachi=intent.getStringExtra("diachi");
        sdt = intent.getStringExtra("sdt");
        txtTienThanhToan.setText("Tổng tiền cần thanh toán: " + tien + " vnđ");
        Picasso.with(getApplicationContext()).load(hinh).into(imgHinh);
        txtTensp.setText(tensp);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtGiaSP.setText("Giá: " + decimalFormat.format(giasp) + " vnđ");
        txtSoLuong.setText("Số lượng: " + sl);
        txtMauSac.setText("Màu: " +mausac );
        txtSize.setText("size: " +size );
        txtTenKhachHang.setText("Tên khách hàng: " +tenkh );
        txtSDT.setText("Sdt: " + sdt);
        item_danhSachHang=new Item_DanhSachHang(id,hinh,tensp,giasp,sl,mausac,size,tenkh,sdt,diachi);
        txtDiaChi.setText("Địa chỉ: "+diachi);
        btnGuiShipper.setOnClickListener(this);
        btnHuyDonHang.setOnClickListener(this);
        btnGoi.setOnClickListener(this);
    }

    @Override
    public void onSucess(String result) {
        if (result.equals("1")){
            initDialogGuiChoShipper();
            dialogDaXacNhan.show();
        }
        else if(result.equals("2")){
            initDialogDonHangHuy();
            dialogDonHangHuy.show();
            sqLiteDonHangHuy.insertHangBiHuy(item_danhSachHang);
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.layout_chitietvekhachhang;
    }

    @Override
    protected void initView() {
        txtDiaChi = (TextView) findViewById(R.id.txtDiaChiKhachHang1);
        txtGiaSP = (TextView) findViewById(R.id.txtGiaSp4);
        txtMauSac = (TextView) findViewById(R.id.txtMauSac1);
        txtSize = (TextView) findViewById(R.id.txtSize1);
        txtTensp = (TextView) findViewById(R.id.txtTenSp3);
        txtDiaChi = (TextView) findViewById(R.id.txtDiaChiKhachHang1);
        txtTenKhachHang = (TextView) findViewById(R.id.txtTenKhachHang1);
        txtSDT = (TextView) findViewById(R.id.txtSDTKhachHang1);
        imgHinh = (ImageView) findViewById(R.id.imgHinhXacNhanMuaHang1);
        txtSoLuong = (TextView) findViewById(R.id.txtSoLuongSp4);
        txtTienThanhToan = (TextView) findViewById(R.id.txtTienThanhToan);
        btnGoi = (Button) findViewById(R.id.btnGoiXacNhan);
        btnHuyDonHang = (Button) findViewById(R.id.btnHuyDonHang);
        btnGuiShipper = (Button) findViewById(R.id.btnGuiChoShipPer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGoiXacNhan:
                String uri = "tel:" +sdt;
                Intent intent1 = new Intent(Intent.ACTION_CALL);
                intent1.setData(Uri.parse(uri));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent1);
                break;
            case R.id.btnHuyDonHang:
                btnHuyDonHang.setEnabled(false);
                xoaDonHang(LoginConfig.xoadonhang(item_danhSachHang.getId()));

                break;
            case R.id.btnGuiChoShipPer:
                btnGuiShipper.setEnabled(false);
                guiShipper(LoginConfig.guiShipper(item_danhSachHang.getTenSp(),String.valueOf(item_danhSachHang.getGiasp()),String.valueOf(item_danhSachHang.getSoluong()),item_danhSachHang.getTenKhachHang(),item_danhSachHang.getsDTKhachHang(),item_danhSachHang.getDiaChiKhachHang()));
                xoaDonHang(LoginConfig.xoadonhang(item_danhSachHang.getId()));
//                CountDownTimer countDownTimer=new CountDownTimer(5000,1000) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                    }
//                }.start();
                sqLiteDonHangHuy.insertHangDaxacNhan(item_danhSachHang);
                break;
        }
    }
    private void initDialogDonHangHuy(){
        dialogDonHangHuy = new Dialog(this);
        dialogDonHangHuy.setContentView(R.layout.dialog_huydonhang);
        dialogDonHangHuy.setCanceledOnTouchOutside(true);
        dialogDonHangHuy.setCancelable(true);
        Button btnOk= (Button) dialogDonHangHuy.findViewById(R.id.btnHuyDonHangThanhCong);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDonHangHuy.dismiss();
                Intent intent=new Intent(Act_XacNhanDonHang.this,Act_QuanLyCuaHang.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
            }
        });
    }
    private void initDialogGuiChoShipper(){
        dialogDaXacNhan = new Dialog(this);
        dialogDaXacNhan.setContentView(R.layout.dialog_guichoshipper);
        dialogDaXacNhan.setCanceledOnTouchOutside(true);
        dialogDaXacNhan.setCancelable(true);
        Button btnOk= (Button) dialogDaXacNhan.findViewById(R.id.btnGuiChoShipperThanhCong);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDaXacNhan.dismiss();
                Intent intent=new Intent(Act_XacNhanDonHang.this,Act_QuanLyCuaHang.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Act_XacNhanDonHang.this,Act_QuanLyCuaHang.class);
        startActivity(intent);
        overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
    }

}
