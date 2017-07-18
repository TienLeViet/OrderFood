package com.example.administrator.orderfood;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 16/07/2017.
 */

public class OrderFoodDatabase {
    public static final String DB_NAME = "Orderfood";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "BanhCanh";

    public static final String ID = "Id";
    public static final int ID_COLUMN = 0;

    public static final String CONTENT = "NoiDung";
    public static final int CONTENT_COLUMN = 1;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREAMENT, " +
                    CONTENT + " TEXT NOT NULL" +
            ");";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
            db.execSQL("INSERT INTO " + TABLE_NAME + "VALUES ('Khong hanh, Binh thuong'");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }

    SQLiteDatabase db;
    SQLiteOpenHelper dbHelper;

    public OrderFoodDatabase(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    public void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    public void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeDB() {
        if (db != null) {
            db.close();
        }
    }

    public ArrayList<BanhCanh> getBanhCanh (String listBanhCanh) {
        this.openReadableDB();
        Cursor cursor = db.query(TABLE_NAME, new String[] {ID, CONTENT}, ID + "= ?", new String[] {String.valueOf(listBanhCanh)}, null, null, null);
        ArrayList<BanhCanh> banhCanhArrayList = new ArrayList<BanhCanh>();
        while (cursor.moveToNext()) {
            banhCanhArrayList.add(getBanhCanhFromCursor(cursor));
        }
        if (cursor != null) {
            cursor.close();
        }
        this.closeDB();
        return banhCanhArrayList;
    }

    private static BanhCanh getBanhCanhFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        } else {
            try {
                BanhCanh banhCanh = new BanhCanh(cursor.getString(CONTENT_COLUMN));
                return banhCanh;
            }catch (Exception e) {
                return null;
            }
        }
    }
}
