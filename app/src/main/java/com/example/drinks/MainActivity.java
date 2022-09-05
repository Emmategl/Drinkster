package com.example.drinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;

/** Acts as the main page when opening the app.
 *  This class check orientations of the screen and the retrieves the correct <MainActivityFragment>
 *      for it.
 *
 */

public class MainActivity extends AppCompatActivity {
    private FragmentManager fm;
    Fragment fragmentUI, fragmentList;


    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemsDB = ItemsDB.get(this);
        fm = getSupportFragmentManager();
        setUpFragments();
    }

    /** Setup the Fragments depending on the screen orientation.
     *
     */
    private void setUpFragments() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentUI= fm.findFragmentById(R.id.container_ui_landscape);
            fragmentList= fm.findFragmentById(R.id.container_list);
            if ((fragmentUI == null) && (fragmentList == null)) {
                fragmentUI= new MainInterfaceFragment();
                fragmentList= new ListFragment();
                fm.beginTransaction()
                        .add(R.id.container_ui_landscape, fragmentUI)
                        .add(R.id.container_list, fragmentList)
                        .commit();
            }
        } else {
            //Orientation portrait
            fragmentUI= fm.findFragmentById(R.id.container_ui_portrait);
            if (fragmentUI== null) {
                fragmentUI = new MainInterfaceFragment();
                fm.beginTransaction()
                        .add(R.id.container_ui_portrait, fragmentUI)
                        .commit();
            }
        }
    }

}