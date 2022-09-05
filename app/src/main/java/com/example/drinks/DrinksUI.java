package com.example.drinks;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/** The class gets the correct fragment depending on the orientation of the screen.
 * (Horizontal is not setup yet)
 * It also takes an additional @String as input from the bundle. To find the drink in the database.
 *
 */

public class DrinksUI extends AppCompatActivity {
    private Drink drink;
    private FragmentManager fm;
    Fragment fragmentDrink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_ui);
        ItemsDB drinks = ItemsDB.get(this);
        //---------------------  Getting the additional information out of the bundle.
        Bundle b = getIntent().getExtras();
        String drinkname = "";
        if (b != null) {
            drinkname = b.getString("key");
        }
        //-----------------------

        fm = getSupportFragmentManager();
        setUpDrinkFragments(drinkname);

        //Orientation portrait

    }

    /** Set up the the fragment depending on the screen being horizontal or vertical.
     * @param drinkName - Current drink searched for.
     * The method uses the drinkname to find the drink in the database and adds its information to the fragment.
     */
    private void setUpDrinkFragments(String drinkName){
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            fragmentDrink = fm.findFragmentById(R.id.container_drink_land);
            if (fragmentDrink==null){
                fragmentDrink = DrinksFragment.newInstance(drinkName);
                fm.beginTransaction()
                        .add(R.id.container_drink_land, fragmentDrink)
                        .commit();
            }
        }
        else {
            fragmentDrink = fm.findFragmentById(R.id.container_drink);
            if (fragmentDrink == null) {
                fragmentDrink = DrinksFragment.newInstance(drinkName);
                fm.beginTransaction()
                        .add(R.id.container_drink, fragmentDrink)
                        .commit();
            }
        }

    }
}
