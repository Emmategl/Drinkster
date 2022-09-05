package com.example.drinks.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.drinks.Drink;

import com.example.drinks.database.ItemsDbSchema.ItemTable;

/**
 * Creates the cursor for the database.
 * Which makes it possible to go though the database and query.
 */
public class ItemCursorWrapper extends CursorWrapper {
    public ItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    /** This method makes it possible to make a query that return every single column in the database
     *
     * @return Drinks.
     */
    public Drink getItem() {
        String what = getString(getColumnIndex(ItemTable.Cols.WHAT));
        String ingredients[] = getString(getColumnIndex(ItemTable.Cols.Ingredients)).split(",");
        String url = getString(getColumnIndex(ItemTable.Cols.URL));
        String information = getString(getColumnIndex(ItemTable.Cols.Information));
        return new Drink(what, ingredients, url, information);
    }

    /** This method makes it possible to make a query that return the name of the drink.
     *
     * @return String with name of drinks
     */
    public String getDrinkName(){
        return getString(getColumnIndex(ItemTable.Cols.WHAT));
    }

}