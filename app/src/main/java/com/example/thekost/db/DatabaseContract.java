package com.example.thekost.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String TABLE_HISTORY = "history";
    public static final class HistoryColumn implements BaseColumns{
        public static String ID = "id";
        public static String NAMA = "nama";
        public static String HARGA = "harga";
        public static String STATUS = "status";
        public static String DITERIMA = "diterima";
        public static String IMAGE = "image";
    }

    public static final String AUTHORITY = "com.example.thekost";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_HISTORY)
            .build();

    public static String getColumnString(Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
