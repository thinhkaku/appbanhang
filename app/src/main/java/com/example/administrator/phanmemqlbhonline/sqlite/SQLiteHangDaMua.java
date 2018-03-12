package com.example.administrator.phanmemqlbhonline.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.administrator.phanmemqlbhonline.model.ItemHangDaMua;
import com.example.administrator.phanmemqlbhonline.model.Item_DanhSachHang;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 11/27/2017.
 */

public class SQLiteHangDaMua {
    public  static  final String PATH= Environment.getDataDirectory().getPath()+"/data/com.example.administrator.phanmemqlbhonline/database/";
    public static final String DB_NAME = "danhsachdonhang.sqlite";
    public static final String TABLE_NAME = "hangdamua";
    public static final String ID = "id";
    public static final String TENSP = "tensp";
    public static final String GIASP = "giasp";
    public static final String SOLUONG = "soluong";
    public static final String MAUSAC = "mausac";
    public static final String SIZE = "size";
    public static final String HINH = "hinh";
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public SQLiteHangDaMua(Context context) {
        this.context = context;
        copyProject();
    }
    private void copyProject(){
        try {
            File file = new File(PATH+DB_NAME);
            if (file.exists()){
                return;
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream outputStream=new FileOutputStream(file);
            InputStream inputStream=context.getAssets().open(DB_NAME);
            byte[] b=new byte[1024];
            int count=inputStream.read(b);
            while (count!=-1){
                outputStream.write(b,0,count);
                count=inputStream.read(b);
            }
            outputStream.close();
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void openDatabase(){
        sqLiteDatabase = context.openOrCreateDatabase(PATH+DB_NAME,Context.MODE_PRIVATE,null);
    }
    private void closeDataBase(){
        sqLiteDatabase.close();
    }

    public ArrayList<ItemHangDaMua> getDataHangDaMua(){
        ArrayList<ItemHangDaMua>arrHangDaMua=new ArrayList<>();
        openDatabase();
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        int indexID=cursor.getColumnIndex(ID);
        int indexTenSP=cursor.getColumnIndex(TENSP);
        int indexGiaSP=cursor.getColumnIndex(GIASP);
        int indexSoLuong=cursor.getColumnIndex(SOLUONG);
        int indexMausac=cursor.getColumnIndex(MAUSAC);
        int indexSize=cursor.getColumnIndex(SIZE);
        int indexHinh=cursor.getColumnIndex(HINH);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            int id=cursor.getInt(indexID);
            String tenSP=cursor.getString(indexTenSP);
            int giaSP=cursor.getInt(indexGiaSP);
            int soLuong=cursor.getInt(indexSoLuong);
            String mauSac=cursor.getString(indexMausac);
            String size=cursor.getString(indexSize);
            String hinh=cursor.getString(indexHinh);
            ItemHangDaMua itemHangDaMua = new ItemHangDaMua(id,tenSP,giaSP,soLuong,mauSac,size,hinh);
            arrHangDaMua.add(itemHangDaMua);
            cursor.moveToNext();
        }
        closeDataBase();
        return arrHangDaMua;
    }

    public long insertHangDaMua(ItemHangDaMua itemHangDaMua){
        ContentValues values = new ContentValues();
        values.put(TENSP,itemHangDaMua.getTenSP());
        values.put(GIASP,itemHangDaMua.getGiaSP());
        values.put(SOLUONG,itemHangDaMua.getSoLuong());
        values.put(MAUSAC,itemHangDaMua.getMauSac());
        values.put(SIZE,itemHangDaMua.getSize());
        values.put(HINH,itemHangDaMua.getHinh());
        openDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME, null, values);
        closeDataBase();
        return id;
    }

    public int delete(int id){
        String[] args = {id+""};
        openDatabase();
        int rows = sqLiteDatabase.delete(TABLE_NAME,ID+"=?",args);
        closeDataBase();
        return rows;
    }

}
