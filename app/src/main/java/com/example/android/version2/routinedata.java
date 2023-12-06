package com.example.android.version2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class routinedata extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "routine.db";
    public static final String TABLE_NAME = "routine_date";
    public static final String COL1 = "count";
    public static final String COL2 = "lap";
    public static final String COL3 = "title";
    public static final String COL4 = "ID";



    public routinedata (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " lap TEXT ,"+
                " title TEXT ,"+
                " count TEXT)";
        db.execSQL(createTable);
    }

    public void insertInitialData(SQLiteDatabase db) {
        delete();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, "33");
        contentValues.put(COL2, "1");
        contentValues.put(COL3, "لا إله الا الله");



        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(COL1, "33");
        contentValues2.put(COL2, "1");
        contentValues2.put(COL3, "الله أكبر");

        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(COL1, "50");
        contentValues3.put(COL2, "2");
        contentValues3.put(COL3, "سبحان الله");

        ContentValues contentValues4 = new ContentValues();
        contentValues4.put(COL1, "99");
        contentValues4.put(COL2, "1");
        contentValues4.put(COL3, "استغفر الله");


        db.insert(TABLE_NAME, null, contentValues);
        db.insert(TABLE_NAME, null, contentValues2);
        db.insert(TABLE_NAME, null, contentValues3);
        db.insert(TABLE_NAME, null, contentValues4);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item1,String item2,String item3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item1);
        contentValues.put(COL2, item2);
        contentValues.put(COL3, item3);


        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor readsearch(String searchKeyword){

        String query = "SELECT * FROM " + TABLE_NAME+" WHERE "+COL1+" LIKE ? OR "+COL2+" LIKE ? OR "+COL3+" LIKE ?";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] searchArgs = {"%" + searchKeyword + "%", "%" + searchKeyword + "%", "%" + searchKeyword + "%"};


        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, searchArgs);
        }
        return cursor;
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor readAllData_inorder(String value){
        String query = "SELECT * FROM " + TABLE_NAME+ " ORDER BY LOWER(title) "+value;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public boolean deleteRoutine(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "ID=?", new String[]{id});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
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

