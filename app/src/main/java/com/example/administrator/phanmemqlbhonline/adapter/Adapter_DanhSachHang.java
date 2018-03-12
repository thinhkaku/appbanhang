package com.example.administrator.phanmemqlbhonline.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.activity.Act_ThongTinNguoiMuaHang;
import com.example.administrator.phanmemqlbhonline.model.ItemNews;
import com.example.administrator.phanmemqlbhonline.sqlite.SQLiteData;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 11/28/2017.
 */

public class Adapter_DanhSachHang extends BaseAdapter {
    private ArrayList<ItemNews>arrDanhSachSp=new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    private SQLiteData sqLiteData;
    private Dialog dialog;
    public Adapter_DanhSachHang(ArrayList<ItemNews> arrDanhSachSp, Context context) {
        this.arrDanhSachSp = arrDanhSachSp;
        this.context = context;
    }
    public void addItemAdapter(ArrayList<ItemNews> itemNew){
        arrDanhSachSp.addAll(itemNew);
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return arrDanhSachSp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrDanhSachSp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final ViewHodler viewHodler;
        if (v==null){
            viewHodler = new ViewHodler();
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item,parent,false);
            viewHodler.imgHinhSpChinh = (ImageView) v.findViewById(R.id.imgHinhChinh);
            viewHodler.txtTenSp = (TextView) v.findViewById(R.id.txtTenSp1);
            viewHodler.txtGiaSp = (TextView) v.findViewById(R.id.txtGiaSP);
            viewHodler.btnDatHang = (TextView) v.findViewById(R.id.btnDatHang1);
            viewHodler.btnThemgioHang= (TextView) v.findViewById(R.id.btnThemGioHang);
            viewHodler.txtTinhTrangSp = (TextView) v.findViewById(R.id.txtTinhTranSp);
            v.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) v.getTag();
        }
        final ItemNews itemNews = arrDanhSachSp.get(position);
        Glide.with(context).load(itemNews.getHinhSpChinh()).placeholder(R.drawable.icon_load).into(viewHodler.imgHinhSpChinh);
        viewHodler.txtTenSp.setText(itemNews.getTenSp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHodler.txtGiaSp.setText("Giá: "+decimalFormat.format(itemNews.getGiaSp())+"vnđ");
        viewHodler.txtTinhTrangSp.setText("Tình trạng: còn hàng");
        viewHodler.btnThemgioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHodler.btnThemgioHang.setClickable(false);
                sqLiteData=new SQLiteData(context);
                sqLiteData.insert(itemNews);
                initDiaLog();
                dialog.show();
            }
        });
        viewHodler.btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Act_ThongTinNguoiMuaHang.class);
                intent.putExtra("tensp1",itemNews.getTenSp());
                intent.putExtra("giasp1",itemNews.getGiaSp());
                intent.putExtra("hinhsp",itemNews.getHinhSpChinh());
                context.startActivity(intent);
            }
        });
        return v;
    }

    class ViewHodler{
        private ImageView imgHinhSpChinh;
        private TextView txtTenSp,txtGiaSp,txtTinhTrangSp;
        private TextView btnDatHang, btnThemgioHang;
    }
    private void  initDiaLog(){
        dialog=new Dialog(context);
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
