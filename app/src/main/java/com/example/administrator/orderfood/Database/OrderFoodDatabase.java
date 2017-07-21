package com.example.administrator.orderfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.administrator.orderfood.Food.BanhCanh;

import java.util.ArrayList;

/**
 * Created by Administrator on 16/07/2017.
 */

public class OrderFoodDatabase {
    public static final String DB_NAME = "Orderfood";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "BanhCanh";

    public static final String ORDER_ID = "Id";
    public static final int ORDER_ID_COLUMN = 0;

    public static final String ORDER_TABLE= "Table";
    public static final int ORDER_TABLE_COLUMN = 1;

    public static final String ORDER_CONTENT = "NoiDung";
    public static final int ORDER_CONTENT_COLUMN = 2;


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREAMENT, " +
                    ORDER_TABLE + " INTEGER NOT NULL" +
                    ORDER_CONTENT + " TEXT NOT NULL" +
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
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(DB_NAME, "Upgrading from " + oldVersion + " to " + newVersion);
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

    public ArrayList<BanhCanh> getBanhCanhs (String listBanhCanh) {
        String where = ORDER_ID + "= ?";
        String[] whereArgs = {listBanhCanh};
        this.openReadableDB();
        Cursor cursor = db.query(TABLE_NAME, null, where, whereArgs, null, null, null);
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

    public BanhCanh getBanhCanh(int id) {
        String where = ORDER_ID + "= ?";
        String[] whereArgs = {Integer.toString(id)};
        this.openReadableDB();
        Cursor cursor = db.query(TABLE_NAME, null, where, whereArgs, null,null, null);
        cursor.moveToFirst();
        BanhCanh banhCanh = getBanhCanhFromCursor(cursor);
        if (cursor != null) {
            cursor.close();
        }
        this.closeDB();
        return banhCanh;
    }

    private static BanhCanh getBanhCanhFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        } else {
            try {
                BanhCanh banhCanh = new BanhCanh(cursor.getInt(ORDER_TABLE_COLUMN), cursor.getString(ORDER_CONTENT_COLUMN));
                return banhCanh;
            }catch (Exception e) {
                return null;
            }
        }
    }

    public long insertBanhCanh(BanhCanh banhCanh) {
        ContentValues cv = new ContentValues();
        cv.put(ORDER_TABLE, banhCanh.getTable());
        cv.put(ORDER_CONTENT, banhCanh.getContent());
        this.openWriteableDB();
        long rowID = db.insert(TABLE_NAME, null, cv);
        this.closeDB();
        return rowID;
    }

    public int updateBanhCanh(BanhCanh banhCanh) {
        ContentValues cv = new ContentValues();
        cv.put(ORDER_TABLE, banhCanh.getTable());
        cv.put(ORDER_CONTENT, banhCanh.getContent());
        String where = ORDER_ID + "= ?";
        String[] whereArgs = {String.valueOf(banhCanh.getId())};
        this.openWriteableDB();
        int rowCount = db.update(TABLE_NAME, cv, where, whereArgs);
        this.closeDB();
        return rowCount;
    }

    public int deleteBanhCanh(long id) {
        String where = ORDER_ID + "= ?";
        String[] whereArgs = {String.valueOf(id)};
        this.openWriteableDB();
        int rowCount = db.delete(TABLE_NAME, where, whereArgs);
        this.closeDB();
        return rowCount;
    }
}
