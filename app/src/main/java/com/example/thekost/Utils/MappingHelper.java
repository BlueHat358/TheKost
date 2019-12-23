package com.example.thekost.Utils;

import android.database.Cursor;

import com.example.thekost.Model.history;

import java.util.ArrayList;

import static com.example.thekost.db.DatabaseContract.HistoryColumn.*;

public class MappingHelper {
    public static ArrayList<history> mapCursorToArrayList(Cursor cursor){
        ArrayList<history> list = new ArrayList<>();

        while(cursor.moveToNext()){
            String nama, status;
            int id, harga, diterima, image;
            nama = cursor.getString(cursor.getColumnIndexOrThrow(NAMA));
            status = cursor.getString(cursor.getColumnIndexOrThrow(STATUS));
            id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            harga = cursor.getInt(cursor.getColumnIndexOrThrow(HARGA));
            diterima = cursor.getInt(cursor.getColumnIndexOrThrow(DITERIMA));
            image = cursor.getInt(cursor.getColumnIndexOrThrow(IMAGE));

            list.add(new history(nama, status, id, harga, diterima, image));
        }

        return list;
    }
}
