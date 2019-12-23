package com.example.thekost.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.thekost.Utils.PublicClassString.STATE;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "thekost.db";

    private static final String CREATE_HISTORY_DB_TABLE = String.format("CREATE TABLE %s" +
            " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            " %s TEXT NULL," +
            " %s TEXT NULL," +
            " %s TEXT NULL," +
            " %s TEXT NULL," +
            " %s INTEGER NULL)",
            DatabaseContract.TABLE_HISTORY,
            DatabaseContract.HistoryColumn._ID,
            DatabaseContract.HistoryColumn.NAMA,
            DatabaseContract.HistoryColumn.HARGA,
            DatabaseContract.HistoryColumn.STATUS,
            DatabaseContract.HistoryColumn.DITERIMA,
            DatabaseContract.HistoryColumn.IMAGE);

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_HISTORY_DB_TABLE);
        Log.e(STATE, "Database onCreate " + sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_HISTORY);
        onCreate(sqLiteDatabase);
    }
}
