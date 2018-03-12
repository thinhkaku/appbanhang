package com.example.administrator.phanmemqlbhonline.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.model.Item_DanhSachHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 11/1/2017.
 */

public class AdapterDanhSachKhachHang extends ArrayAdapter<Item_DanhSachHang> {
    private LayoutInflater inflater;
    private ArrayList<Item_DanhSachHang>arrayListDanhSach= new ArrayList<>();
    public AdapterDanhSachKhachHang(@NonNull Context context, @NonNull ArrayList<Item_DanhSachHang> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        inflater=LayoutInflater.from(context);
        arrayListDanhSach = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        ViewHoder viewHoder;
        if (v==null){
            viewHoder = new ViewHoder();
            v=inflater.inflate(R.layout.item_danh_sach_khach_hang,parent,false);
            viewHoder.imgHinh= (ImageView) v.findViewById(R.id.imgHinhChinh1);
            viewHoder.txtTenSp = (TextView) v.findViewById(R.id.txtTenSp);
            viewHoder.txtGiaSP = (TextView) v.findViewById(R.id.txtGiaSP3);
            viewHoder.txtSoLuong = (TextView) v.findViewById(R.id.txtSoLuong);
            viewHoder.txtMauSac = (TextView) v.findViewById(R.id.txtMauSac);
            viewHoder.txtSize = (TextView) v.findViewById(R.id.txtSize);
            viewHoder.txtTenKhachHang = (TextView) v.findViewById(R.id.txtTenKhachHang);
            viewHoder.txtSDTKhachHang= (TextView) v.findViewById(R.id.txtSDT);
            viewHoder.txtDiaChiKhachHang= (TextView) v.findViewById(R.id.txtDiaChiKhachHang);
            v.setTag(viewHoder);
        }else {
            viewHoder= (ViewHoder) v.getTag();
        }
        Item_DanhSachHang item_danhSachHang = arrayListDanhSach.get(position);
        Picasso.with(getContext()).load(item_danhSachHang.getHinh()).into(viewHoder.imgHinh);
        viewHoder.txtTenSp.setText(item_danhSachHang.getTenSp());
        int tien=item_danhSachHang.getGiasp()*item_danhSachHang.getSoluong();
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHoder.txtGiaSP.setText("Tổng tiền : "+decimalFormat.format(tien)+" vnđ");
        viewHoder.txtSoLuong.setText("Số lượng: "+item_danhSachHang.getSoluong()+"");
        viewHoder.txtMauSac.setText("Màu sắc: "+item_danhSachHang.getMauSac());
        viewHoder.txtSize.setText("Kích cỡ: "+item_danhSachHang.getSizeSp());
        viewHoder.txtTenKhachHang.setText("Tên KH: "+item_danhSachHang.getTenKhachHang());
        viewHoder.txtSDTKhachHang.setText("Sdt: "+item_danhSachHang.getsDTKhachHang());
        viewHoder.txtDiaChiKhachHang.setText("Địa chỉ: "+item_danhSachHang.getDiaChiKhachHang());
        return v;
    }
    class ViewHoder{
        private ImageView imgHinh;
        private TextView txtTenSp,txtGiaSP,txtSoLuong,txtMauSac,txtSize,txtTenKhachHang,txtSDTKhachHang,txtDiaChiKhachHang;
    }
}
