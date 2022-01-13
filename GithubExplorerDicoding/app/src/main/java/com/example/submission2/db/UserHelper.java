package com.example.submission2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.submission2.db.DatabaseContract.TABLE_NAME;
import static com.example.submission2.db.DatabaseContract.UserColumns.USERNAME;

public class UserHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper dataBaseHelper;
    private static UserHelper INSTANCE;

    private static SQLiteDatabase database;

    public UserHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static UserHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }


    public Cursor reqAll() {
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                USERNAME + " DESC");
    }

    public Cursor reqById(String id) {
        return database.query(
                DATABASE_TABLE,
                null
                , USERNAME + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public long insertDB(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateDB(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, USERNAME + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, USERNAME + " = ?", new String[]{id});
    }


}
