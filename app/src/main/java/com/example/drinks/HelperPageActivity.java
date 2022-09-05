package com.example.drinks;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/** Class setups of the activity for the helper page.
 *
 *
 */
public class HelperPageActivity extends AppCompatActivity {

    private FragmentManager fm;
    Fragment helperUi;


    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_ui);
        itemsDB = ItemsDB.get(this);
        fm = getSupportFragmentManager();
        setUpFragments();
    }

    /** Set up Fragment so it fits the screen orientation.
     *
     */
    private void setUpFragments() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            helperUi= fm.findFragmentById(R.id.container_drink_land);
            if ((helperUi == null) && (helperUi == null)) {
                helperUi= new HelperPageFragment();
                fm.beginTransaction()
                        .add(R.id.container_drink_land, helperUi)
                        .commit();
            }
        } else {
            //Orientation portrait
            helperUi= fm.findFragmentById(R.id.container_drink);
            if (helperUi== null) {
                helperUi = new HelperPageFragment();
                fm.beginTransaction()
                        .add(R.id.container_drink, helperUi)
                        .commit();
            }
        }
    }


}
