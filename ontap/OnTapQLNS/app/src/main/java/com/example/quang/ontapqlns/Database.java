package com.example.quang.ontapqlns;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Quang on 1/4/2018.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "QLNS";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "_table_QLNS";
    public static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "_hoten";
    private static final String COLUMN_DEGREE = "_bangcap";
    private static final String COLUMN_HOBBIE = "_sothich";


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME +
                " TEXT," + COLUMN_DEGREE + " TEXT," + COLUMN_HOBBIE + " TEXT)");
    }

    public void saveItem(ArrayList<QLNS> nhanSu) {
        SQLiteDatabase db = getWritableDatabase();
        for (QLNS nhanvien : nhanSu) {
            if(!findMember(nhanvien)) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_NAME, nhanvien.getHoten());
                values.put(COLUMN_DEGREE, nhanvien.getBangcap());
                values.put(COLUMN_HOBBIE, nhanvien.getSothich());
                db.insert(TABLE_NAME, null, values);
            }
        }
        db.close();
    }

    public boolean findMember(QLNS nhanvien) {
        boolean found = false;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_NAME,COLUMN_DEGREE,COLUMN_HOBBIE}, COLUMN_NAME + " =? AND " + COLUMN_DEGREE + " =? AND " + COLUMN_HOBBIE + " =?",new String[]{nhanvien.getHoten(),nhanvien.getBangcap() + "",nhanvien.getSothich()},null,null,null);
        if(cursor.moveToFirst()){
            found = true;
        }
        return found;
    }

    public void loadItem(ArrayList<QLNS> nhanSu){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{COLUMN_NAME,COLUMN_DEGREE,COLUMN_HOBBIE},null,null,null,null,COLUMN_NAME);
        if(cursor.moveToFirst()){
            do {
                String hoten = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                int bangcap = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DEGREE)));
                String sothich = cursor.getString(cursor.getColumnIndex(COLUMN_HOBBIE));
                nhanSu.add(new QLNS(hoten,bangcap,sothich));
            }while(cursor.moveToNext());
        }
        db.close();
    }

    public void deleteItem(QLNS nhanvien){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_NAME + " =? AND " + COLUMN_DEGREE + " =? AND " + COLUMN_HOBBIE + " =?",new String[]{nhanvien.getHoten(),nhanvien.getBangcap() + "",nhanvien.getSothich()});
        db.close();
    }

    public void updateItem(QLNS nhanvien, QLNS nhanvienMoi){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,nhanvienMoi.getHoten());
        values.put(COLUMN_DEGREE,nhanvienMoi.getBangcap());
        values.put(COLUMN_HOBBIE,nhanvienMoi.getSothich());

        db.update(TABLE_NAME,values,COLUMN_NAME + " =? AND " + COLUMN_DEGREE + " =? AND " + COLUMN_HOBBIE + " =?",new String[]{nhanvien.getHoten(),nhanvien.getBangcap() + "",nhanvien.getSothich()});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
