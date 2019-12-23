package com.example.thekost.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String TABLE_HISTORY = "history";
    public static final class HistoryColumn implements BaseColumns{
        public static String NAMA = "nama";
        public static String HARGA = "harga";
        public static String STATUS = "status";
        public static String DITERIMA = "diterima";
        public static String IMAGE = "image";
    }
}
