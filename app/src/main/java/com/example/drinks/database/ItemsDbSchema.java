package com.example.drinks.database;

/** Class setup the names for columns in the SQLite database
 * As well as the name for the database itself.
 *
 */

public class ItemsDbSchema {


    public static final class ItemTable {
        public static final String NAME = "Items";

        public static final class Cols {
            public static final String WHAT = "what";
            public static final String Ingredients = "Ingredients";
            public static final String URL = "URL";
            public static final String Information = "info";
            //public static final byte[] picture = "picture";
        }
    }
}
