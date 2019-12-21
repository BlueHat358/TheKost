package com.example.thekost.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String HISTORY_DB_NAME = "history.db";

    private static final String CREATE_HISTORY_DB_TABLE = String.format("CREATE TABLE %s" +
            " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_HISTORY,
            DatabaseContract.HistoryColumn.ID,
            DatabaseContract.HistoryColumn.NAMA,
            DatabaseContract.HistoryColumn.HARGA,
            DatabaseContract.HistoryColumn.STATUS,
            DatabaseContract.HistoryColumn.DITERIMA,
            DatabaseContract.HistoryColumn.IMAGE);

    public DatabaseHelper(Context context){
        super(context, HISTORY_DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_HISTORY_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_HISTORY);
        onCreate(sqLiteDatabase);
    }
}
