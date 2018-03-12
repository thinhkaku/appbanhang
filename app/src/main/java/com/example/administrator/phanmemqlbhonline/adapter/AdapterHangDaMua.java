package com.example.administrator.phanmemqlbhonline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.model.ItemHangDaMua;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 11/27/2017.
 */

public class AdapterHangDaMua extends ArrayAdapter<ItemHangDaMua> {
    private LayoutInflater inflater;
    private ArrayList<ItemHangDaMua>arrHangDaMua=new ArrayList<>();
    public AdapterHangDaMua(@NonNull Context context,  @NonNull ArrayList<ItemHangDaMua> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        inflater=LayoutInflater.from(context);
        arrHangDaMua=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        ViewHodler viewHodler;
        if (v==null){
            viewHodler=new ViewHodler();
            v=inflater.inflate(R.layout.item_hangdamua,parent,false);
            viewHodler.imgHinh=(ImageView)v.findViewById(R.id.imgHinhDaMua);
            viewHodler.txtTenSp=(TextView)v.findViewById(R.id.txtTenSpDaMua);
            v.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) v.getTag();
        }
        ItemHangDaMua itemHangDaMua =arrHangDaMua.get(position);
        viewHodler.txtTenSp.setText(itemHangDaMua.getTenSP());
        Picasso.with(getContext()).load(itemHangDaMua.getHinh()).into(viewHodler.imgHinh);
        return v;
    }

    class ViewHodler{
        private ImageView imgHinh;
        private TextView txtTenSp;
    }
}
