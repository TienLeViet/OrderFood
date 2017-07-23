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
    public static final String DB_NAME = "OrderFood";
    public static final int DB_VERSION = 1;

    // Tab thức ăn và nước uống.
    public static final String LIST_TABLE = "list";

    public static final String LIST_ID = "id";
    public static final int LIST_ID_COL = 0;

    public static final String LIST_NAME = "list_name";
    public static final int LIST_NAME_COL = 1;

    // Nội dung trong listview của tab thức ăn hoặc nước uống.
    public static final String TASK_TABLE = "task";

    public static final String TASK_ID = "id";
    public static final int TASK_ID_COL = 0;

    public static final String TASK_LIST_ID = "list_id";
    public static final int TASK_LIST_ID_COL = 1;

    public static final String TASK_NUMBER = "Ban";
    public static final int TASK_NUMBER_COL = 2;

    public static final String TASK_CONTENT = "NoiDung";
    public static final int TASK_CONTENT_COL = 3;

    // Tạo bảng.
    public static final String CREATE_LIST_TABLE =
            "CREATE TABLE " + LIST_TABLE + " (" +
                    LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LIST_NAME + " TEXT NOT NULL UNIQUE);";

    public static final String CREATE_TASK_TABLE =
            "CREATE TABLE " + TASK_TABLE + " (" +
                    TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TASK_LIST_ID + " INTEGER NOT NULL, " +
                    TASK_NUMBER + " INTEGER NOT NULL, " +
                    TASK_CONTENT + " TEXT NOT NULL);";

    public static final String DROP_LIST_TABLE =
            "DROP TABLE IF EXISTS " + LIST_TABLE;

    public static final String DROP_TASK_TABLE =
            "DROP TABLE IF EXISTS " + TASK_TABLE;


    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_LIST_TABLE);
            db.execSQL(CREATE_TASK_TABLE);

            // Insert default lists.
            db.execSQL("INSERT INTO list VALUES (1, 'Foods')");
            db.execSQL("INSERT INTO list VALUES (2, 'Drinks')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(DB_NAME, "Upgrading from " + oldVersion + " to " + newVersion);
            db.execSQL(DROP_LIST_TABLE);
            db.execSQL(DROP_TASK_TABLE);
            onCreate(db);
        }
    }

    // database object and database helper object.
    private  SQLiteDatabase db;
    private DBHelper dbHelper;

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

    public ArrayList<List> getList() {
        ArrayList<List> lists = new ArrayList<List>();
        openReadableDB();
        Cursor cursor = db.query(LIST_TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            List list = new List();
            list.setId(cursor.getInt(LIST_ID_COL));
            list.setName(cursor.getString(LIST_NAME_COL));
            lists.add(list);
        }
        if (cursor != null) {
            cursor.close();
        }
        closeDB();
        return lists;
    }

    public List getList(String name) {
        String where = LIST_NAME + "= ?";
        String[] whereArgs = {name};
        openReadableDB();
        Cursor cursor = db.query(LIST_TABLE, null, where, whereArgs, null, null, null);
        List list = null;
        cursor.moveToFirst();
        list = new List(cursor.getInt(LIST_ID_COL), cursor.getString(LIST_NAME_COL));
        if (cursor != null) {
            cursor.close();
        }
        this.closeDB();
        return list;
    }

    public ArrayList<Task> getTasks (String listName) {
        String where = TASK_LIST_ID + "= ?";
        int listID = getList(listName).getId();
        String[] whereArgs = {Integer.toString(listID)};
        this.openReadableDB();
        Cursor cursor = db.query(TASK_TABLE, null, where, whereArgs, null, null, null);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (cursor.moveToNext()) {
            tasks.add(getTaskFromCursor(cursor));
        }
        if (cursor != null) {
            cursor.close();
        }
        this.closeDB();
        return tasks;
    }

    public Task getTask(int id) {
        String where = TASK_ID + "= ?";
        String[] whereArgs = {Integer.toString(id)};
        this.openReadableDB();
        Cursor cursor = db.query(TASK_TABLE, null, where, whereArgs, null,null, null);
        cursor.moveToFirst();
        Task task = getTaskFromCursor(cursor);
        if (cursor != null) {
            cursor.close();
        }
        this.closeDB();
        return task;
    }

    private static Task getTaskFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        } else {
            try {
                Task task = new Task(cursor.getInt(TASK_LIST_ID_COL),
                        cursor.getInt(TASK_NUMBER_COL),
                        cursor.getString(TASK_CONTENT_COL));
                return task;
            }catch (Exception e) {
                return null;
            }
        }
    }

    public long insertTask(Task task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK_LIST_ID, task.getListId());
        cv.put(TASK_NUMBER, task.getNumber());
        cv.put(TASK_CONTENT, task.getContent());
        this.openWriteableDB();
        long rowID = db.insert(TASK_TABLE, null, cv);
        this.closeDB();
        return rowID;
    }

    public int updateTask(Task task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK_LIST_ID, task.getListId());
        cv.put(TASK_NUMBER, task.getNumber());
        cv.put(TASK_CONTENT, task.getContent());
        String where = TASK_ID + "= ?";
        String[] whereArgs = {String.valueOf(task.getId())};
        this.openWriteableDB();
        int rowCount = db.update(TASK_TABLE, cv, where, whereArgs);
        this.closeDB();
        return rowCount;
    }

    public int deleteTask(long id) {
        String where = TASK_ID + "= ?";
        String[] whereArgs = {String.valueOf(id)};
        this.openWriteableDB();
        int rowCount = db.delete(TASK_TABLE, where, whereArgs);
        this.closeDB();
        return rowCount;
    }
}
