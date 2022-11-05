package com.example.two_factor_auth_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABAE_NAME = "Pin_Database";
    private static final String TABLE_NAME = "Pin";
    private static final String COL1 = "Phone_no";
    private static final String COL2 = "pin";
    private static final int DATABAE_VERSION = 1;


    public DBHelper(@Nullable Context context, @Nullable String DATABAE_NAME, @Nullable SQLiteDatabase.CursorFactory factory, int DATABAE_VERSION) {
        super(context, DATABAE_NAME, factory, DATABAE_VERSION);
    }

    public DBHelper(Context context) {
        super(context, DATABAE_NAME, null, DATABAE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME +"("+ COL1 +" TEXT,"+COL2+" NUMBER)";

        db.execSQL(query);
    }

    public void insertdata(String phoneno , int pin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL1,phoneno);
        values.put(COL2,pin);

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public void updatedata(String phoneno , int pin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL1,phoneno);
        values.put(COL2,pin);

        db.update(TABLE_NAME,values,"name=?",null);

        db.close();
    }

    public int readdata(String phoneno) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);


        int check_pin = 0;
        String phone_no = null;
        if (cursor.moveToFirst()) {
            do {
                 phone_no= cursor.getString(0);
                check_pin = cursor.getInt(1);
            } while (cursor.moveToNext());
        }

        cursor.close();
//        if (phoneno == phone_no) {
//            return check_pin;
//        }
        return check_pin;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        onCreate(db);
    }
}
