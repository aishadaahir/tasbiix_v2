package com.example.android.version2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class datedata extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "reset2.db";
    public static final String TABLE_NAME = "reset_date";
    public static final String COL1 = "count";
    public static final String COL2 = "date";
    public static final String COL3 = "type";
    public static final String COL4 = "title";




    public datedata (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ( count TEXT, " +
                " date TEXT ,"+
                " type TEXT ,"+
                " title TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item1,String item2,String item3,String item4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item1);
        contentValues.put(COL2, item2);
        contentValues.put(COL3, item3);
        contentValues.put(COL4, item4);


        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor readsearch(String searchKeyword){

        String query = "SELECT * FROM " + TABLE_NAME+" WHERE "+COL1+" LIKE ? OR "+COL2+" LIKE ? OR "+COL3+" LIKE ? OR "+COL4+" LIKE ?";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] searchArgs = {"%" + searchKeyword + "%", "%" + searchKeyword + "%", "%" + searchKeyword + "%", "%" + searchKeyword + "%"};


        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, searchArgs);
        }
        return cursor;
    }


    public Cursor readAllData_inorder(String value){
        String query = "SELECT * FROM " + TABLE_NAME+ " ORDER BY rowid "+value;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

//    public Cursor readAllData_inorder(String value){
//        String query = "SELECT * FROM " + TABLE_NAME+ " ORDER BY substr(date, 5) "+value;
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if(db != null){
//            cursor = db.rawQuery(query, null);
//        }
//        return cursor;
//    }

//    public Cursor readAllData(){
//        String query = "SELECT * FROM " + TABLE_NAME+ " ORDER BY substr(date, 5)DESC";
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if(db != null){
//            cursor = db.rawQuery(query, null);
//        }
//        return cursor;
//    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME+ " ORDER BY rowid DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public boolean delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, null, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}

