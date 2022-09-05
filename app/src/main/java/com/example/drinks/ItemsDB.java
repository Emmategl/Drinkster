package com.example.drinks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;
import com.example.drinks.database.*;

/**
 * Class is acting as storage for the different types of drinks. Furthermore is can also add
 * drinks to the list if it's called.
 */

public class ItemsDB extends Observable {

    private static ItemsDB staticItemsDB;
    private static SQLiteDatabase mDatabase;



    /** Creates the static ItemsDB
     * And checks if there is already created an SQLite database
     */
    public static ItemsDB get(Context context){
        if (staticItemsDB == null) {
            mDatabase= new ItemBaseHelper(context.getApplicationContext()).getWritableDatabase();
            staticItemsDB = new ItemsDB(context);
        }

        return  staticItemsDB;
    }

    /** Initializes class and calls fillList(context)
     *
     * @param context
     */
    private ItemsDB(Context context){
        fillList(context);
    }


    /** Method adds Drinks to database.
     * Before adding to database it checks if the database already have the drink in the system or not.
     * It does the above using the <ItemsCursorWrapper>.
     * If the database already contains the drink the methods doesn't add it and returns a String with information regarding this.
     * If the database doesn't contain the drink it's inserted to the database and the <Observer> is notified for the Recyclerview.
     * The methods returns a String depending on the result of the action. If it manages to add to the database. It returns a String with information about item added.
     * @param nameC parameter for drink
     * @param ingre parameter for drink
     * @param url parameter for drink
     * @param info parameter for drink
     * @return a String depending of the ressult of what happened.
     */
    public String addDrink(String nameC, String ingre , String url, String info) {
        String name = nameC.toLowerCase();
        String [] ingredients  =  ingre.split(",");
        Drink newDrink= new Drink(nameC, ingredients, url, info);
        ItemCursorWrapper cursor= queryItems("what like '"+name+"'", null);
       if (cursor.moveToFirst()) {
           cursor.close();
           return "Item Already in storage";
       } else {
           cursor.close();
           ContentValues values = getContentValues(newDrink);
           mDatabase.insert(ItemsDbSchema.ItemTable.NAME, null, values);
           this.setChanged();
           notifyObservers();
           return "\nDrink \nName: " + name +"\n Has now been added to the system.\n";
       }
    }


    /** Method reads the JsonFile <drinks.json> and loops over the Json array.
     * In the loop it attempts to add each individual drink to the database<s
     * @param context
     */
    public void fillList(Context context){
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("drinks.json")));

            String jsonString = "";
            String line = "";

                    while ((line = reader.readLine())!=null){
                        jsonString+=line;
                    }
            JSONArray itemsA= new JSONArray(jsonString);
            for (int i= 0;(i<itemsA.length()); i++) {
                addDrink( itemsA.getJSONObject(i).getString("drink"),
                        itemsA.getJSONObject(i).getString("ingredients"),itemsA.getJSONObject(i).getString("URL"),itemsA.getJSONObject(i).getString("Information"));
            }
        } catch (JSONException je) {  System.out.println("Failed to parse JSON");
        } catch (IOException e) { System.out.println("Failed to read file"); }
    }


    /** Method takes in a String and then do a query in the datbase.
     * Where it's trying to find a match between drink name and the String.
     * If a match is found it returns the Drink. Els it returns null
     * @param input
     * @return Either returns a drink or null if no match found.
     */
    public Drink searchForDrink(String input) {
        ItemCursorWrapper cursor = queryItems("what like '" + input + "'", null);
        if (cursor.moveToFirst()) {
            Drink drink = cursor.getItem();
            return drink;
        }
        cursor.close();
        return null;
    }

    /**Add the information of a <Drink> to the SQLite database.
     *
     * @param drink
     * @return the content values.
     */
    private static ContentValues getContentValues(Drink drink) {
        ContentValues values=  new ContentValues();
        values.put(ItemsDbSchema.ItemTable.Cols.WHAT, drink.getDrinkName());
        values.put(ItemsDbSchema.ItemTable.Cols.Ingredients, drink.getIngredients());
        values.put(ItemsDbSchema.ItemTable.Cols.URL, drink.getURL());
        values.put(ItemsDbSchema.ItemTable.Cols.Information, drink.getDescription());
        return values;
    }

    /** Set up a query where one pass a <Where> clause to it.
     *
     * @param whereClause
     * @param whereArgs
     * @return a <ItemCursorWrapper>
     */
    static private ItemCursorWrapper queryItems(String whereClause, String[] whereArgs) {
        Cursor cursor= mDatabase.query(
                ItemsDbSchema.ItemTable.NAME,
                null, // Columns - null selects all columns
                whereClause, whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new ItemCursorWrapper(cursor);
    }

    public void close() {   mDatabase.close();   }


    /** Method takes in a parameter to find a drink in the database.
     * If a matching drink is found then it's removed from the database.
     * @param name - drinkName
     */
    public void removeItem(String name){
        Drink newItem= new Drink(name);
        String selection= ItemsDbSchema.ItemTable.Cols.WHAT + " LIKE ?";
        int changed= mDatabase.delete(ItemsDbSchema.ItemTable.NAME, selection, new String[]{newItem.getDrinkName()});
        if (changed > 0) { this.setChanged(); notifyObservers();  }
    }


    /**Returns an ArrayList with all Drinks in the database.
     *
     * @return
     */
    public ArrayList<Drink> listItems() {
        ArrayList<Drink> drinks = new ArrayList<>();

        ItemCursorWrapper cursor= queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            drinks.add(cursor.getItem());
            cursor.moveToNext();
        }
        cursor.close();






        return drinks;
    }
}
