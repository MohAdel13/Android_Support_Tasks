package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ShoeDatabaseHelper extends SQLiteOpenHelper {
    private final String TABLE_NAME = "SHOE_TABLE";
    private final String COLUMN_BRAND = "BRAND";
    private final String COLUMN_ID = "ID";
    private final String COLUMN_SIZE = "SIZE";
    private final String COLUMN_MODEL = "MODEL";
    private final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public ShoeDatabaseHelper(@Nullable Context context) {
        super(context,"shoe.dp",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creationQuery = "CREATE TABLE "+TABLE_NAME+" ( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_BRAND+" TEXT NOT NULL, "+COLUMN_SIZE+" INTEGER NOT NULL, "+
                COLUMN_DESCRIPTION+" TEXT NOT NULL, "+COLUMN_MODEL+" TEXT NOT NULL )";
        db.execSQL(creationQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertShoe(ShoeModel shoe)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BRAND,shoe.brand);
        cv.put(COLUMN_SIZE,shoe.size);
        cv.put(COLUMN_DESCRIPTION,shoe.description);
        cv.put(COLUMN_MODEL,shoe.model);
        db.insert(TABLE_NAME,null, cv);
    }
    public List<ShoeModel> getShoes(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<ShoeModel> ShoeList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if (cursor.moveToFirst())
        {
            do {
                String shoeBrand = cursor.getString(1);
                int shoeSize = cursor.getInt(2);
                String shoeDescription = cursor.getString(3);
                String shoeModel = cursor.getString(4);
                ShoeModel shoe = new ShoeModel(shoeBrand,shoeSize,shoeDescription,shoeModel);
                ShoeList.add(shoe);
            }while(cursor.moveToNext());
        }
        return ShoeList;
    }
}
