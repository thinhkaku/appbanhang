package com.example.administrator.phanmemqlbhonline.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.administrator.phanmemqlbhonline.model.ItemNews;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 10/18/2017.
 */

public class SQLiteData {
    public static final String PATH =
            Environment.getDataDirectory().getPath()
                    + "/data/com.example.administrator.phanmemqlbhonline/database/";

    public static final String DB_NAME = "banhangonline.sqlite";
    public static final String TABLE_NAME = "danhsachhang";
    public static final String ID = "id";
    public static final String GIASP = "giasp";
    public static final String TENSP = "tensp";
    public static final String HA1 = "ha1";
    public static final String HA2 = "ha2";
    public static final String HA3 = "ha3";
    public static final String HA4 = "ha4";
    public static final String HA5 = "ha5";
    public static final String MOTA = "mota";
    public static final String IDLOAISP = "idloaisp";
    private Context context;
    private SQLiteDatabase database;

    public SQLiteData(Context context) {
        this.context = context;
        copyDatabaseToProject();
    }
    private void copyDatabaseToProject() {
        try {
            File file = new File(PATH + DB_NAME);
            if (file.exists()) {
                return;
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            InputStream inputStream = context.getAssets().open(DB_NAME);
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
        database = context.openOrCreateDatabase(PATH + DB_NAME,Context.MODE_PRIVATE,null);
    }

    public void closeDatabase(){
        database.close();
    }

    public ArrayList<ItemNews> getData(){
        ArrayList<ItemNews>arrayList = new ArrayList<>();
        openDatabase();
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,null);
        int indexId= cursor.getColumnIndex(ID);
        int indexTensp= cursor.getColumnIndex(TENSP);
        int indexGiaSp= cursor.getColumnIndex(GIASP);
        int indexH1= cursor.getColumnIndex(HA1);
        int indexH2= cursor.getColumnIndex(HA2);
        int indexH3= cursor.getColumnIndex(HA3);
        int indexH4= cursor.getColumnIndex(HA4);
        int indexH5= cursor.getColumnIndex(HA5);
        int indexMoTa= cursor.getColumnIndex(MOTA);
        int indexLoaiSp= cursor.getColumnIndex(IDLOAISP);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            int id=cursor.getInt(indexId);
            String tensp = cursor.getString(indexTensp);
            int giasp = cursor.getInt(indexGiaSp);
            String ha1 = cursor.getString(indexH1);
            String ha2 = cursor.getString(indexH2);
            String ha3 = cursor.getString(indexH3);
            String ha4 = cursor.getString(indexH4);
            String ha5 = cursor.getString(indexH5);
            String mota = cursor.getString(indexMoTa);
            int idloaisp = cursor.getInt(indexLoaiSp);
            ItemNews itemNews = new ItemNews(id,ha1,tensp,giasp,ha2,ha3,ha4,ha5,mota,idloaisp);
            arrayList.add(itemNews);
            cursor.moveToNext();
        }
        closeDatabase();
        return arrayList;
    }

    public long insert(ItemNews itemNews){
        ContentValues values = new ContentValues();
        values.put(TENSP,itemNews.getTenSp());
        values.put(GIASP,itemNews.getGiaSp());
        values.put(HA1,itemNews.getHinhSpChinh());
        values.put(HA2,itemNews.getHinh1());
        values.put(HA3,itemNews.getHinh2());
        values.put(HA4,itemNews.getHinh3());
        values.put(HA5,itemNews.getHinh4());
        values.put(MOTA,itemNews.getMoTaSp());
        values.put(IDLOAISP,itemNews.getIdLoaiSp());
        openDatabase();
        long id = database.insert(TABLE_NAME, null, values);
        closeDatabase();
        return id;
    }
    public int delete(int id){
        String[] args = {id+""};
        openDatabase();
        int rows = database.delete(TABLE_NAME,ID+"=?",args);
        closeDatabase();
        return rows;
    }
}
