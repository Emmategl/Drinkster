package com.example.drinks.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.drinks.database.ItemsDbSchema.ItemTable;

/**
 * Class creates and setup the SQLite database used in the APP.
 */

public class ItemBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "Drinks.db";
    public ItemBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ItemTable.NAME + "(" +
                ItemTable.Cols.WHAT + ", " + ItemTable.Cols.Ingredients +
                ", " + ItemTable.Cols.Information + ", " + ItemTable.Cols.URL + " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}

