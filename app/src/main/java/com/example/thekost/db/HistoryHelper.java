package com.example.thekost.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.thekost.Model.history;
import com.example.thekost.Model.model;
import com.example.thekost.db.DatabaseContract.*;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.thekost.Utils.PublicClassString.STATE;
import static com.example.thekost.db.DatabaseContract.TABLE_HISTORY;

public class HistoryHelper {
    private static final String HISTORY = TABLE_HISTORY;

    private Context context;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static HistoryHelper INSTANCE;

    public HistoryHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public static HistoryHelper getINSTANCE(Context context){
        if(INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new HistoryHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLiteException{
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
                HISTORY,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }

    public Cursor queryById(String id){
        return database.query(
                HISTORY,
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
        return database.insert(HISTORY, null, values);
    }

    public int update(String id, ContentValues values){
        return database.update(HISTORY, values, _ID + " = ?", new String[]{id});
    }

    public int deleteById(String id){
        return database.delete(HISTORY, _ID + " = ?", new String[]{id});
    }
}
