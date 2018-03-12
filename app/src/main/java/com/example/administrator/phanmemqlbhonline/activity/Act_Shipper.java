package com.example.administrator.phanmemqlbhonline.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.phanmemqlbhonline.R;
import com.example.administrator.phanmemqlbhonline.adapter.Adapter_Shipper;
import com.example.administrator.phanmemqlbhonline.config.LoginConfig;
import com.example.administrator.phanmemqlbhonline.loginsystem.BaseActivity;
import com.example.administrator.phanmemqlbhonline.model.ItemShipper;
import com.example.administrator.phanmemqlbhonline.model.Item_DanhSachHang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 11/6/2017.
 */

public class Act_Shipper extends BaseActivity {
    private ListView lvShipper;
    private ArrayList<ItemShipper>arrShipper;
    private Adapter_Shipper adapter_shipper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        laydulieuShipper(LoginConfig.laydulieuShipper());
        initView();
    }

    @Override
    public void onSucess(String result) {
        arrShipper.clear();
        try {
            JSONArray jsonArray = new JSONArray(result);
            //JSONObject jsonObject = new JSONObject(result);
            for (int i =0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id=jsonObject.getInt("id");
                String tenSp = jsonObject.getString("tensp");
                int giasp = jsonObject.getInt("giasp");
                int  soluong=jsonObject.getInt("sl");
                String tenKhachHang = jsonObject.getString("tenkh");
                String sDt = jsonObject.getString("sdt");
                String diaChi=jsonObject.getString("diachi");
                ItemShipper itemShipper = new ItemShipper(id,tenSp,giasp,soluong,tenKhachHang,sDt,diaChi);
                arrShipper.add(itemShipper);
                adapter_shipper.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.layout_shipper;
    }

    @Override
    protected void initView() {
        lvShipper= (ListView) findViewById(R.id.lvShipper);
        arrShipper=new ArrayList<>();
        adapter_shipper=new Adapter_Shipper(Act_Shipper.this,arrShipper);
        lvShipper.setAdapter(adapter_shipper);
    }
}
