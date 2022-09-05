package com.example.drinks;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/** Class set up the fragments for the Manage Database.
 *
 */


public class ManageDatabaseActivity extends AppCompatActivity {
    private FragmentManager fm;
    Fragment fragmentUI;


    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_ui);
        itemsDB = ItemsDB.get(this);
        fm = getSupportFragmentManager();
        setUpFragments();
    }

    /** Setup the Fragments depending on the screen orientation.
     *
     */
    private void setUpFragments() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentUI= fm.findFragmentById(R.id.container_drink_land);

            if ((fragmentUI == null)) {
                fragmentUI= new ManageDatabaseUI();

                fm.beginTransaction()
                        .add(R.id.container_drink_land, fragmentUI)
                        .commit();
            }
        } else {
            //Orientation portrait
            fragmentUI= fm.findFragmentById(R.id.container_drink);
            if (fragmentUI== null) {
                fragmentUI = new ManageDatabaseUI();
                fm.beginTransaction()
                        .add(R.id.container_drink, fragmentUI)
                        .commit();
            }
        }
    }

}



