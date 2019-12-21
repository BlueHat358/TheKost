package com.example.thekost.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import com.example.thekost.Model.history;
import com.example.thekost.Model.model;
import com.example.thekost.db.DatabaseContract.*;

import java.util.ArrayList;

public class HistoryHelper {
    private static String HISTORY = DatabaseContract.TABLE_HISTORY;

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public HistoryHelper(Context context){
        this.context = context;
    }

    public HistoryHelper open() throws SQLiteException{
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        sqLiteDatabase.close();
    }

    public Cursor DataHistory(){
        String DATABASE_TABLE = HISTORY;
        return sqLiteDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE + " ORDER BY "+
                HistoryColumn.ID + " ASC", null);
    }

    public ArrayList<history> getDataHistory(){
        history history_;

        ArrayList<history> list = new ArrayList<>();
        Cursor cursor = DataHistory();

        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            do{
                history_ = new history();
                history_.setId(cursor.getInt(cursor.getColumnIndexOrThrow(HistoryColumn.ID));
                history_.setNama(cursor.getString(cursor.getColumnIndexOrThrow(HistoryColumn.NAMA)));
                history_.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(HistoryColumn.STATUS)));
                history_.setHarga();cursor.getInt(cursor.getColumnIndexOrThrow(HistoryColumn.HARGA));
                history_.setDiterima();cursor.getInt(cursor.getColumnIndexOrThrow(HistoryColumn.DITERIMA));
                history_.setImage();cursor.getInt(cursor.getColumnIndexOrThrow(HistoryColumn.IMAGE));

                list.add(history_);

                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }

        cursor.close();
        return list;
    }

    public long insert(history history_) {
        String DATABASE_TABLE = HISTORY;
        ContentValues initialValues = new ContentValues();
        initialValues.put(HistoryColumn.NAMA, history_.getNama());
        initialValues.put(HistoryColumn.STATUS, history_.getStatus());
        initialValues.put(HistoryColumn.HARGA, history_.getHarga());
        initialValues.put(HistoryColumn.DITERIMA, history_.getDiterima());
        initialValues.put(HistoryColumn.IMAGE, history_.getImage());
        return sqLiteDatabase.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(history history_){
        String DATABASE_TABLE = HISTORY;
        ContentValues initialValues = new ContentValues();
        initialValues.put(HistoryColumn.NAMA, history_.getNama());
        initialValues.put(HistoryColumn.STATUS, history_.getStatus());
        initialValues.put(HistoryColumn.HARGA, history_.getHarga());
        initialValues.put(HistoryColumn.DITERIMA, history_.getDiterima());
        initialValues.put(HistoryColumn.IMAGE, history_.getImage());
        return sqLiteDatabase.update(DATABASE_TABLE, initialValues, HistoryColumn.ID +
                "= '" + history_.getId() + "'", null);
    }

    public void delete(int id) {
        String DATABASE_TABLE = HISTORY;
        sqLiteDatabase.delete(DATABASE_TABLE, HistoryColumn._ID + "= '" + id +
                "'", null);
    }

    public void beginTransaction(){
        sqLiteDatabase.beginTransaction();
    }

    public void setTransactionSuccess(){
        sqLiteDatabase.setTransactionSuccessful();
    }

    public void endTransaction(){
        sqLiteDatabase.endTransaction();
    }

    public void insertTransaction(ArrayList<history> history_) {
        String DATABASE_TABLE = HISTORY;

        String sql = "INSERT INTO " + DATABASE_TABLE + " (" +
                HistoryColumn.NAMA + ", " +
                HistoryColumn.STATUS + ", " +
                HistoryColumn.HARGA + ", " +
                HistoryColumn.DITERIMA + ", " +
                HistoryColumn.IMAGE + ") VALUES (?, ?, ?, ?, ?)";

        beginTransaction();

        SQLiteStatement stmt = sqLiteDatabase.compileStatement(sql);
        for (int i = 0; i < history_.size(); i++) {
            stmt.bindString(1, history_.get(i).getNama());
            stmt.bindString(2, history_.get(i).getStatus());
            stmt.bindDouble(3, history_.get(i).getHarga());
            stmt.bindDouble(4, history_.get(i).getDiterima());
            stmt.bindDouble(5, history_.get(i).getImage());
            stmt.execute();
            stmt.clearBindings();
        }

        setTransactionSuccess();
        endTransaction();
    }
}
