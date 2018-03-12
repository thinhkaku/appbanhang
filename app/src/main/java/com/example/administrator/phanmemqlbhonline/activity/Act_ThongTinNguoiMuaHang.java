package com.example.administrator.phanmemqlbhonline.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.phanmemqlbhonline.MainActivity;
import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.config.LoginConfig;
import com.example.administrator.phanmemqlbhonline.loginsystem.BaseActivity;
import com.example.administrator.phanmemqlbhonline.model.ItemHangDaMua;
import com.example.administrator.phanmemqlbhonline.sqlite.SQLiteHangDaMua;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 10/31/2017.
 */

public class Act_ThongTinNguoiMuaHang extends BaseActivity {
    private ImageView imgHinhXacNhanMuaHang;
    private TextView txtTenSP, txtGiaSp;
    private Dialog dialogDatHangThanhCong,dialogHoiLaiCoChacChanDatHang;
    private EditText edtSoLuong,edtMauSac,edtSize,edtTen,edtSDT,edtDiaChi;
    private Button btnXacNhanMuaHang;
    private SQLiteHangDaMua sqLiteHangDaMua;
    private int tongTien,giasp;
    private Spinner spSize;
    private ArrayAdapter<String>arrayAdapter;
    private String soLuong, tenSp,ten, sdt,mausac,size,diaChi,hinh;
    private ItemHangDaMua itemHangDaMua;
    private String item[]={"S","M","L","XL","XXL"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        sqLiteHangDaMua = new SQLiteHangDaMua(this);
        initView();
        addEvent();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.layout_thong_tin_nguoi_mua_hang;
    }

    private void addEvent() {
        Intent intent = getIntent();
        hinh=intent.getStringExtra("hinhsp");
        Picasso.with(getApplicationContext()).load(hinh).into(imgHinhXacNhanMuaHang);
        tenSp=intent.getStringExtra("tensp1");
        txtTenSP.setText(tenSp);
        giasp=intent.getIntExtra("giasp1",0);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtGiaSp.setText("Giá: "+decimalFormat.format(giasp)+" vnđ");
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,item);
        spSize.setAdapter(arrayAdapter);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                size=item[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnXacNhanMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong=edtSoLuong.getText()+"";
                ten=edtTen.getText()+"";
               sdt=edtSDT.getText()+"";
                diaChi=edtDiaChi.getText()+"";
                 mausac=edtMauSac.getText()+"";
                 //size=edtSize.getText()+"";

                if (soLuong.isEmpty()||ten.isEmpty()||sdt.isEmpty()||diaChi.isEmpty()){
                    Snackbar.make(v,"Bạn chưa nhập đủ thông tin cần thiết",Snackbar.LENGTH_SHORT).show();
                }else {
                    tongTien=Integer.parseInt(soLuong)*giasp;
                    initDialogHoiLai();
                    dialogHoiLaiCoChacChanDatHang.show();
                }
            }
        });
    }

    protected void initView() {
        imgHinhXacNhanMuaHang = (ImageView) findViewById(R.id.imgHinhXacNhanMuaHang);
        txtTenSP= (TextView) findViewById(R.id.txtTenSp2);
        txtGiaSp= (TextView) findViewById(R.id.txtGiaSp2);
        edtSoLuong= (EditText) findViewById(R.id.edtSoLuongSp);
        edtMauSac= (EditText) findViewById(R.id.edtMauSac);
        edtTen= (EditText) findViewById(R.id.edtTenNguoiMua);
        edtSDT= (EditText) findViewById(R.id.edtSDT);
        edtDiaChi= (EditText) findViewById(R.id.edtDiaChi);
        btnXacNhanMuaHang= (Button) findViewById(R.id.btnXacNhanMuaHang);
        spSize= (Spinner) findViewById(R.id.spSize);
    }

    @Override
    public void onSucess(String result) {
        if (result.equals("1")){
            sqLiteHangDaMua.insertHangDaMua(itemHangDaMua);
            initDialogDatHangThanhCong();
            dialogDatHangThanhCong.show();
        }else {
            Toast.makeText(Act_ThongTinNguoiMuaHang.this,"Bạn đã đặt hàng thất bại",Toast.LENGTH_SHORT).show();
        }
    }
    private void initDialogDatHangThanhCong(){
        dialogDatHangThanhCong= new Dialog(this);
        dialogDatHangThanhCong.setContentView(R.layout.layout_thongbaodathangthanhcong);
        dialogDatHangThanhCong.setCancelable(true);
        dialogDatHangThanhCong.setCanceledOnTouchOutside(true);
        Button btnDatThanhCong= (Button) dialogDatHangThanhCong.findViewById(R.id.btnDongDatHangThanhCong);
        btnDatThanhCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Act_ThongTinNguoiMuaHang.this,MainActivity.class);
                startActivity(intent);
                dialogDatHangThanhCong.dismiss();
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_edt);
            }
        });
    }

    private void initDialogHoiLai(){
        dialogHoiLaiCoChacChanDatHang=new Dialog(this);
        dialogHoiLaiCoChacChanDatHang.setContentView(R.layout.dialog_thongbaohoilaichacchandathang);
        dialogHoiLaiCoChacChanDatHang.setCancelable(true);
        dialogHoiLaiCoChacChanDatHang.setCanceledOnTouchOutside(true);
        TextView txtThongBao =(TextView)dialogHoiLaiCoChacChanDatHang.findViewById(R.id.txtThongBao);
        Button btnDongY =(Button) dialogHoiLaiCoChacChanDatHang.findViewById(R.id.btnDialogDongy);
        Button btnhuy =(Button) dialogHoiLaiCoChacChanDatHang.findViewById(R.id.btnDialogCancel);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtThongBao.setText("Bạn có chắc chắn mua "+soLuong+" "+tenSp+" với tổng tiền là: "+decimalFormat.format(tongTien)+" vnđ");
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogHoiLaiCoChacChanDatHang.dismiss();
                itemHangDaMua=new ItemHangDaMua(1,tenSp,giasp,Integer.parseInt(soLuong),mausac,size,hinh);
                String api=LoginConfig.taiLenThongTinKhachKhang(hinh,tenSp,giasp, Integer.parseInt(soLuong),mausac,size,ten,sdt,diaChi);
                guiThongtiN(api);
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogHoiLaiCoChacChanDatHang.dismiss();
            }
        });
    }

}
