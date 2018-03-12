package com.example.administrator.phanmemqlbhonline.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.model.ItemShipper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 11/6/2017.
 */

public class Adapter_Shipper extends ArrayAdapter<ItemShipper> {
    private LayoutInflater inflater;
    private ArrayList<ItemShipper>arrShipper=new ArrayList<>();
    public Adapter_Shipper(@NonNull Context context, @NonNull ArrayList<ItemShipper> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        inflater=LayoutInflater.from(context);
        arrShipper=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        ViewHoDer viewHoDer;
        if(v==null){
            viewHoDer=new ViewHoDer();
            v=inflater.inflate(R.layout.item_shipper,parent,false);
            viewHoDer.txtTensp= (TextView) v.findViewById(R.id.txtTenSP4);
            viewHoDer.txtGiasp= (TextView) v.findViewById(R.id.txtGiaSP5);
            viewHoDer.txtSL= (TextView) v.findViewById(R.id.txtSL);
            viewHoDer.txtTenkh= (TextView) v.findViewById(R.id.txtTenKhachHang2);
            viewHoDer.txtSDT= (TextView) v.findViewById(R.id.txtSDTKhachHang2);
            viewHoDer.txtDiachi= (TextView) v.findViewById(R.id.txtDiaChiKhachhang2);
            viewHoDer.txtTongtien= (TextView) v.findViewById(R.id.txtTongtien);
            v.setTag(viewHoDer);
        }else {
            viewHoDer= (ViewHoDer) v.getTag();
        }
        ItemShipper itemShipper =arrShipper.get(position);
        int giasp=itemShipper.getGiasp();
        int sl=itemShipper.getSl();
        int tongTien=giasp*sl;
        viewHoDer.txtTensp.setText(itemShipper.getTensp());
        viewHoDer.txtTenkh.setText("Tên KH: "+itemShipper.getTenKh());
        viewHoDer.txtSDT.setText("Sdt: "+itemShipper.getSdt());
        viewHoDer.txtDiachi.setText("Địa chỉ: "+itemShipper.getDiachi());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHoDer.txtGiasp.setText("Giá: "+decimalFormat.format(giasp)+" vnđ");
        viewHoDer.txtSL.setText("Số lượng: "+sl);
        viewHoDer.txtTongtien.setText("Tổng tiền phải thu: "+decimalFormat.format(tongTien)+" vnđ");
        return v;
    }

    class ViewHoDer{
        private TextView txtTensp,txtGiasp,txtSL,txtTenkh,txtSDT,txtDiachi,txtTongtien;
    }
}
