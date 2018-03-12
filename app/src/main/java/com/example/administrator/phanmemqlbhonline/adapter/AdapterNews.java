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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.phanmemqlbhonline.activity.Act_ThongTinNguoiMuaHang;
import com.example.administrator.phanmemqlbhonline.model.ItemNews;
import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.sqlite.SQLiteData;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 10/18/2017.
 */

public class AdapterNews extends ArrayAdapter<ItemNews>{
    private LayoutInflater inflater;
    private Dialog dialog;
    private Context context;
    private SQLiteData sqLiteData;
    private ArrayList<ItemNews>dsarr= new ArrayList<>();
    public AdapterNews(@NonNull Context context, @NonNull ArrayList<ItemNews> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        dsarr = objects;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View v, @NonNull final ViewGroup parent) {
        final ViewHodler viewHodler;
        if (v==null){
            viewHodler = new ViewHodler();
            v= inflater.inflate(R.layout.item,parent,false);
            viewHodler.imgHinhSpChinh = (ImageView) v.findViewById(R.id.imgHinhChinh);
            viewHodler.txtTenSp = (TextView) v.findViewById(R.id.txtTenSp1);
            viewHodler.txtGiaSp = (TextView) v.findViewById(R.id.txtGiaSP);
            viewHodler.btnDatHang = (TextView) v.findViewById(R.id.btnDatHang1);
            viewHodler.btnThemgioHang= (TextView) v.findViewById(R.id.btnThemGioHang);
            viewHodler.txtTinhTrangSp = (TextView) v.findViewById(R.id.txtTinhTranSp);
            v.setTag(viewHodler);
        }else {
            viewHodler = (ViewHodler) v.getTag();
        }
         final ItemNews itemNews = dsarr.get(position);
        Glide.with(getContext()).load(itemNews.getHinhSpChinh()).placeholder(R.drawable.icon_load).into(viewHodler.imgHinhSpChinh);
        viewHodler.txtTenSp.setText(itemNews.getTenSp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHodler.txtGiaSp.setText("Giá: "+decimalFormat.format(itemNews.getGiaSp())+"vnđ");
        viewHodler.txtTinhTrangSp.setText("Tình trạng: còn hàng");
        viewHodler.btnThemgioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHodler.btnThemgioHang.setClickable(false);
                sqLiteData=new SQLiteData(getContext());
                sqLiteData.insert(itemNews);
                initDiaLog();
                dialog.show();
            }
        });
        viewHodler.btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Act_ThongTinNguoiMuaHang.class);
                intent.putExtra("tensp1",itemNews.getTenSp());
                intent.putExtra("giasp1",itemNews.getGiaSp());
                intent.putExtra("hinhsp",itemNews.getHinhSpChinh());
                getContext().startActivity(intent);
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
