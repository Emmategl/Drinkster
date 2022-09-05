/** The Drink class is setting up each individual drink object
 *
 */

package com.example.drinks;


import java.util.ArrayList;

/**
 * The class creates a fairly simple drink object.
 *
 */

public class Drink {
    private String name;
    private String[] Ingredients;
    private String url;
    private String description;

    /**Needs name, ingredients, url and text to initialize
     *
     * @param name
     * @param ingre
     * @param url
     * @param text
     */
    public Drink(String name, String[] ingre, String url, String text){
        String add = "https://";
        if (!url.contains(add)) {
            this.url = add+url;
        } else {
            this.url = url;
        }
        this.name = name;
        this.Ingredients = ingre;
        this.description = text;
    }

    public Drink(String name){
        this.name = name;
        this.url = null;
        this.Ingredients = null;
        this.description = null;
    }

    /** It appends the different ingredients to a String.
     *
     * @return a String of ingredients seperated by commas.
     */
    public String getIngredients(){
        String toreturn = "";
        String komma = "";

        for (int i = 0; i < Ingredients.length ; i++) {
            toreturn+=komma;
            toreturn+=Ingredients[i];
            komma = ",";

        }
        return toreturn;

    }

    /** Converts the Array to an ArrayList
     * This is used when creating a recycler view for the ingredients.
     *
     * @return ArrayList<ingredients>
     */
    public ArrayList<String> convertIngredientsArrayToArrayList(){
        ArrayList<String> ingredientsAL = new ArrayList<>();
        for (int i = 0; i < Ingredients.length ; i++) {
            ingredientsAL.add(Ingredients[i]);
        }
        return ingredientsAL;
    }


    /** Returns URL
     *
     * @return url
     */
    public String getURL() {return url;}

    /**Returns drink descriptions
     *
     * @return description
     */
    public String getDescription() { return description;}

    /**
     *
     * @return name
     */
    public String getDrinkName(){
        return name;
    }

}
