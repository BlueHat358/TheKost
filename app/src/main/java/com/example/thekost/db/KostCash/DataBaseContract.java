package com.example.thekost.db.KostCash;

import android.provider.BaseColumns;

public class DataBaseContract {
    public static final String TABLE_TOPUP = "topup";
    public static final class TopUpColumn implements BaseColumns {
        public static String NOMINAL = "nominal";
        public static String BONUS = "bonus";
    }
}
