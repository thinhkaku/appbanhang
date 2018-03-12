package com.example.administrator.phanmemqlbhonline.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.phanmemqlbhonline.activity.Act_ThongTinNguoiMuaHang;
import com.example.administrator.phanmemqlbhonline.model.ItemNews;
import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.sqlite.SQLiteData;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 10/29/2017.
 */

public class AdapterChiTietSP extends ArrayAdapter<ItemNews> {
    private LayoutInflater inflater;
    private Dialog dialog;
    private ArrayList<ItemNews>arrayList= new ArrayList<>();
    private ViewHodler viewHodler;
    private SQLiteData sqLiteData=new SQLiteData(getContext());
    public AdapterChiTietSP(@NonNull Context context, @NonNull ArrayList<ItemNews> objects) {
        super(context, android.R.layout.activity_list_item, objects);
        inflater=LayoutInflater.from(context);
        arrayList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        if (v==null){
            viewHodler = new ViewHodler();
            v=inflater.inflate(R.layout.item_chi_tiet_sp,parent,false);
            viewHodler.imgHinhchinh= (ImageView) v.findViewById(R.id.imgHinh11);
            viewHodler.imgHinh1= (ImageView) v.findViewById(R.id.imgHinh1);
            viewHodler.imgHinh2= (ImageView) v.findViewById(R.id.imgHinh2);
            viewHodler.imgHinh3= (ImageView) v.findViewById(R.id.imgHinh3);
            viewHodler.imgHinh4= (ImageView) v.findViewById(R.id.imgHinh4);
            viewHodler.imgHinh5= (ImageView) v.findViewById(R.id.imgHinh5);
            viewHodler.txtTenSp= (TextView) v.findViewById(R.id.txtTenspChitiet);
            viewHodler.txtMota= (TextView) v.findViewById(R.id.txtMota);
            viewHodler.txtGiaSp= (TextView) v.findViewById(R.id.txtGiaspChiTiet);
            viewHodler.btnDatHang= (Button) v.findViewById(R.id.btnDatHang);
            viewHodler.btnLuu= (Button) v.findViewById(R.id.btnLuuVaoGioHang);
            v.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) v.getTag();
        }
        final ItemNews itemNews=arrayList.get(position);
        viewHodler.txtTenSp.setText(itemNews.getTenSp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHodler.txtGiaSp.setText("Giá: "+decimalFormat.format(itemNews.getGiaSp())+"vnđ");
        viewHodler.txtMota.setText(itemNews.getMoTaSp());
        String hinh2=itemNews.getHinh1();
        String hinh3=itemNews.getHinh2();
        String hinh4=itemNews.getHinh3();
        String hinh5=itemNews.getHinh4();
//        if (hinh3.isEmpty()){
//            viewHodler.imgHinh3.setMaxHeight(0);
//            viewHodler.imgHinh3.setMaxWidth(0);
//        }else {
//            Glide.with(getContext()).load(hinh3).placeholder(R.drawable.icon_load).into(viewHodler.imgHinh3);
//        }
//        if (hinh2.isEmpty()){
//            viewHodler.imgHinh2.setMaxHeight(0);
//            viewHodler.imgHinh2.setMaxWidth(0);
//        }else {
//            Glide.with(getContext()).load(hinh2).placeholder(R.drawable.icon_load).into(viewHodler.imgHinh2);
//        }
//        if (hinh4.equals("1")){
//            viewHodler.imgHinh4.setMaxHeight(0);
//            viewHodler.imgHinh4.setMaxWidth(0);
//        }else {
//            Glide.with(getContext()).load(hinh4).placeholder(R.drawable.icon_load).into(viewHodler.imgHinh4);
//        }
//        if (hinh5.equals("1")){
//            viewHodler.imgHinh5.setMaxHeight(0);
//            viewHodler.imgHinh5.setMaxWidth(0);
//        }else {
//            Glide.with(getContext()).load(hinh5).placeholder(R.drawable.icon_load).into(viewHodler.imgHinh5);
//        }
        Glide.with(getContext()).load(hinh5).into(viewHodler.imgHinh5);
        Glide.with(getContext()).load(hinh4).into(viewHodler.imgHinh4);
        Glide.with(getContext()).load(hinh3).into(viewHodler.imgHinh3);
        Glide.with(getContext()).load(itemNews.getHinhSpChinh()).placeholder(R.drawable.icon_load).into(viewHodler.imgHinhchinh);
        Glide.with(getContext()).load(itemNews.getHinhSpChinh()).placeholder(R.drawable.icon_load).into(viewHodler.imgHinh1);
        Glide.with(getContext()).load(itemNews.getHinh1()).into(viewHodler.imgHinh2);
        viewHodler.btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(getContext(), Act_ThongTinNguoiMuaHang.class);
                intent.putExtra("tensp1",itemNews.getTenSp());
                intent.putExtra("giasp1",itemNews.getGiaSp());
                intent.putExtra("hinhsp",itemNews.getHinhSpChinh());
                getContext().startActivity(intent);
            }
        });
        viewHodler.btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHodler.btnLuu.setClickable(false);
                sqLiteData.insert(itemNews);
                initDiaLog();
                dialog.show();
            }
        });
        return v;
    }
    class ViewHodler{
        private ImageView imgHinhchinh,imgHinh1,imgHinh2,imgHinh3,imgHinh4,imgHinh5;
        private TextView txtMota, txtTenSp,txtGiaSp;
        private Button btnDatHang,btnLuu;
    }

    private void  initDiaLog(){
        dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_thongbaoluuthanhcong);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Button button = (Button) dialog.findViewById(R.id.btnDongY);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
