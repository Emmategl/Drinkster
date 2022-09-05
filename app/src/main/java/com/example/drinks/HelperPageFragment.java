package com.example.drinks;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/** Creates the fragment for the helper page. The helper page being a page explaining the use of the app.
 *
 *
 */
public class HelperPageFragment extends Fragment {

    private Button general, drinkPage, manageDB, mainPage;
    private TextView textbox;



    /** Initializes variables on create
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /** Creates View for helperFragment and adds functionality
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     * @throws NullPointerException
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws NullPointerException {
        final View v= inflater.inflate(R.layout.information_page, container, false);
        general = v.findViewById(R.id.GeneralInformation);
        drinkPage= v.findViewById(R.id.DrinkPageInformation);
        manageDB= v.findViewById(R.id.ManageDatabase);
        mainPage= v.findViewById(R.id.MainPageInformation);
        textbox = v.findViewById(R.id.information_text_information_page);

        /**
         * Simply changes the color of the header and changes the "explanation" text. Depending what is clicked on
         */
        general.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               general.setTextColor(Color.BLACK);
                drinkPage.setTextColor(Color.BLACK);
                manageDB.setTextColor(Color.BLACK);
                mainPage.setTextColor(Color.BLACK);

                general.setTextColor(Color.RED);
                textbox.setText("Drinksters is app where you have the possibility to look up different drinks in our database and get more information about the drink and its ingredients. The app contains three pages with different functions. Click on the different page headers above to learn more about the different pages!");
            }
        });
        drinkPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                general.setTextColor(Color.BLACK);
                drinkPage.setTextColor(Color.BLACK);
                manageDB.setTextColor(Color.BLACK);
                mainPage.setTextColor(Color.BLACK);

                drinkPage.setTextColor(Color.RED);
                textbox.setText("The drink page is the page showing the information on the different drinks. Here one can see a picture of the drink (if updated) and the ingredients going into the drink. Furthermore there is button one can click upon to go to a webpage showing more about the drink.");
            }
        });
        manageDB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                general.setTextColor(Color.BLACK);
                drinkPage.setTextColor(Color.BLACK);
                manageDB.setTextColor(Color.BLACK);
                mainPage.setTextColor(Color.BLACK);

                manageDB.setTextColor(Color.RED);
                textbox.setText("The Manage Database page makes it possible for the user to add and remove drinks from the database. Here the user can add a drink with all the needed information. The User can also remove current drinks from the system. ");
            }
        });
        mainPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                general.setTextColor(Color.BLACK);
                drinkPage.setTextColor(Color.BLACK);
                manageDB.setTextColor(Color.BLACK);
                mainPage.setTextColor(Color.BLACK);

                mainPage.setTextColor(Color.RED);
                textbox.setText("The Main Page is the one you arrive on when you open the app and is acting as a navigation point. Here the user can navigate to the other pages through the buttons as well as searching for Drinks");
            }
        });

        return v;
    }



}
