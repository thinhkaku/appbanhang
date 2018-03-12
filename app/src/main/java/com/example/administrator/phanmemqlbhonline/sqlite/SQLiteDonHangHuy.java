package com.example.administrator.phanmemqlbhonline.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.administrator.phanmemqlbhonline.model.ItemNews;
import com.example.administrator.phanmemqlbhonline.model.Item_DanhSachHang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 11/3/2017.
 */

public class SQLiteDonHangHuy {
    public  static  final String PATH1= Environment.getDataDirectory().getPath()+"/data/com.example.administrator.phanmemqlbhonline/database/";
    public static final String DB_NAME1 = "donhanghuy.sqlite";
    public static final String TABLE_NAME1 = "danhsachdonhanghuy";
    public static final String TABLE_NAME2 = "hangdaxacnhan";
    public static final String ID = "id";
    public static final String TENSP = "tensp";
    public static final String GIASP = "giasp";
    public static final String SOLUONG = "soluong";
    public static final String MAUSAC = "mausac";
    public static final String SIZE = "size";
    public static final String TENKH = "tenkh";
    public static final String SDT = "sdt";
    public static final String DIACHI = "diachi";
    public static final String HINH = "hinh";
    private Context context;
    private SQLiteDatabase database;

    public SQLiteDonHangHuy(Context context) {
        this.context = context;
        copyDatabaseToProject();
    }
    private void copyDatabaseToProject() {
        try {
            File file = new File(PATH1 + DB_NAME1);
            if (file.exists()) {
                return;
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            InputStream inputStream = context.getAssets().open(DB_NAME1);
            byte[] b = new byte[1024];
            int count = inputStream.read(b);
            while (count!=-1){
                outputStream.write(b,0,count);
                count = inputStream.read(b);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void openDatabase(){
        database = context.openOrCreateDatabase(PATH1 + DB_NAME1,Context.MODE_PRIVATE,null);
    }
    public void closeDatabase(){
        database.close();
    }

    public ArrayList<Item_DanhSachHang> getDataHangBiHuy(){
        ArrayList<Item_DanhSachHang>arrayList = new ArrayList<>();
        openDatabase();
        Cursor cursor = database.query(TABLE_NAME1,null,null,null,null,null,null);
        int indexId= cursor.getColumnIndex(ID);
        int indexTensp= cursor.getColumnIndex(TENSP);
        int indexGiaSp= cursor.getColumnIndex(GIASP);
        int indexSoLuong= cursor.getColumnIndex(SOLUONG);
        int indexMauSac= cursor.getColumnIndex(MAUSAC);
        int indexSize= cursor.getColumnIndex(SIZE);
        int indexTenKH= cursor.getColumnIndex(TENKH);
        int indexSDT= cursor.getColumnIndex(SDT);
        int indexDiaChi= cursor.getColumnIndex(DIACHI);
        int indexHinh= cursor.getColumnIndex(HINH);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            int id=cursor.getInt(indexId);
            String tensp = cursor.getString(indexTensp);
            int giasp = cursor.getInt(indexGiaSp);
            int soluong = cursor.getInt(indexSoLuong);
            String mausac = cursor.getString(indexMauSac);
            String size = cursor.getString(indexSize);
            String tenkh = cursor.getString(indexTenKH);
            String sdt = cursor.getString(indexSDT);
            String diachi = cursor.getString(indexDiaChi);
            String hinh = cursor.getString(indexHinh);
            Item_DanhSachHang item_danhSachHang = new Item_DanhSachHang(id,hinh,tensp,giasp,soluong,mausac,size,tenkh,sdt,diachi);
            arrayList.add(item_danhSachHang);
            cursor.moveToNext();
        }
        closeDatabase();
        return arrayList;
    }

    public ArrayList<Item_DanhSachHang> getDataHangDaXacNhan(){
        ArrayList<Item_DanhSachHang>arrayList = new ArrayList<>();
        openDatabase();
        Cursor cursor = database.query(TABLE_NAME2,null,null,null,null,null,null);
        int indexId= cursor.getColumnIndex(ID);
        int indexTensp= cursor.getColumnIndex(TENSP);
        int indexGiaSp= cursor.getColumnIndex(GIASP);
        int indexSoLuong= cursor.getColumnIndex(SOLUONG);
        int indexMauSac= cursor.getColumnIndex(MAUSAC);
        int indexSize= cursor.getColumnIndex(SIZE);
        int indexTenKH= cursor.getColumnIndex(TENKH);
        int indexSDT= cursor.getColumnIndex(SDT);
        int indexDiaChi= cursor.getColumnIndex(DIACHI);
        int indexHinh= cursor.getColumnIndex(HINH);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            int id=cursor.getInt(indexId);
            String tensp = cursor.getString(indexTensp);
            int giasp = cursor.getInt(indexGiaSp);
            int soluong = cursor.getInt(indexSoLuong);
            String mausac = cursor.getString(indexMauSac);
            String size = cursor.getString(indexSize);
            String tenkh = cursor.getString(indexTenKH);
            String sdt = cursor.getString(indexSDT);
            String diachi = cursor.getString(indexDiaChi);
            String hinh = cursor.getString(indexHinh);
            Item_DanhSachHang item_danhSachHang = new Item_DanhSachHang(id,hinh,tensp,giasp,soluong,mausac,size,tenkh,sdt,diachi);
            arrayList.add(item_danhSachHang);
            cursor.moveToNext();
        }
        closeDatabase();
        return arrayList;
    }
    public long insertHangBiHuy(Item_DanhSachHang item_danhSachHang){
        ContentValues values = new ContentValues();
        values.put(TENSP,item_danhSachHang.getTenSp());
        values.put(GIASP,item_danhSachHang.getGiasp());
        values.put(SOLUONG,item_danhSachHang.getSoluong());
        values.put(MAUSAC,item_danhSachHang.getMauSac());
        values.put(SIZE,item_danhSachHang.getSizeSp());
        values.put(TENKH,item_danhSachHang.getTenKhachHang());
        values.put(SDT,item_danhSachHang.getsDTKhachHang());
        values.put(DIACHI,item_danhSachHang.getDiaChiKhachHang());
        values.put(HINH,item_danhSachHang.getHinh());
        openDatabase();
        long id = database.insert(TABLE_NAME1, null, values);
        closeDatabase();
        return id;
    }

    public long insertHangDaxacNhan(Item_DanhSachHang item_danhSachHang){
        ContentValues values = new ContentValues();
        values.put(TENSP,item_danhSachHang.getTenSp());
        values.put(GIASP,item_danhSachHang.getGiasp());
        values.put(SOLUONG,item_danhSachHang.getSoluong());
        values.put(MAUSAC,item_danhSachHang.getMauSac());
        values.put(SIZE,item_danhSachHang.getSizeSp());
        values.put(TENKH,item_danhSachHang.getTenKhachHang());
        values.put(SDT,item_danhSachHang.getsDTKhachHang());
        values.put(DIACHI,item_danhSachHang.getDiaChiKhachHang());
        values.put(HINH,item_danhSachHang.getHinh());
        openDatabase();
        long id = database.insert(TABLE_NAME2, null, values);
        closeDatabase();
        return id;
    }
    public int delete1(int id){
        String[] args = {id+""};
        openDatabase();
        int rows = database.delete(TABLE_NAME1,ID+"=?",args);
        closeDatabase();
        return rows;
    }
}
