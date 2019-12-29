package com.example.thekost.db.KostCash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.thekost.db.DatabaseHelper;

import static android.provider.BaseColumns._ID;
import static com.example.thekost.Utils.PublicClassString.STATE;
import static com.example.thekost.db.KostCash.DataBaseContract.TABLE_TOPUP;

public class TopUpHelper {
    private static final String TOPUP = TABLE_TOPUP;

    private Context context;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static TopUpHelper INSTANCE;

    public TopUpHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public static TopUpHelper getINSTANCE(Context context){
        if(INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new TopUpHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLiteException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();

        if(database.isOpen()){
            database.close();
        }
    }

    public Cursor queryAll(){
        return database.query(
                TOPUP,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }

    public Cursor queryById(String id){
        return database.query(
                TOPUP,
                null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    public long insert(ContentValues values){
        Log.d(STATE, "insert = " + values.toString());
        return database.insert(TOPUP, null, values);
    }

    public int update(String id, ContentValues values){
        return database.update(TOPUP, values, _ID + " = ?", new String[]{id});
    }

    public int deleteById(String id){
        return database.delete(TOPUP, _ID + " = ?", new String[]{id});
    }
}
