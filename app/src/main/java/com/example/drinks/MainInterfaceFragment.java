package com.example.drinks;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/** Class is handling the UI of the main page. The page have ability to:
 * - Search for a drink in the database and open the drink page if the drink is found.
 * - List All items. Where the page open the intent of ListFragment
 * - Adding a drink to database.
 *
 */
public class MainInterfaceFragment extends Fragment {
    private Button search; // Button to click on when searching
    private Button listAllItems; // Button to click on to see all items.
    private TextView output; // A hidden text box showing if no drink found
    private EditText input; // Text input field
    private static ItemsDB drinks;
    private Button manageDBUI;
    private Drink currentDrink;
    private LinearLayout textBoxHolder;
    private ImageButton help;
    @Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        drinks = ItemsDB.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws NullPointerException {
        final View v = inflater.inflate(R.layout.main, container, false);
        textBoxHolder = v.findViewById(R.id.testboxHolder);
        output = v.findViewById(R.id.items);
        search = v.findViewById(R.id.list_button);
        input = v.findViewById(R.id.textInput);
        help = v.findViewById(R.id.informationButton);
        manageDBUI = v.findViewById(R.id.go_to_managedb);
        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE)
            listAllItems = v.findViewById(R.id.list_All_items);


       textBoxHolder.setVisibility(View.INVISIBLE);

        /**
         * On Click listener for ListAllDrinks
         */
        try {


        listAllItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }



        });}
    catch (NullPointerException e){}

        /**
         * On Click listener for Manage Database
         */

        try {
            manageDBUI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ManageDatabaseActivity.class);
                    startActivity(intent);
                }
            });}
        catch (NullPointerException e){}

        /**
         *  On click listener for helper page.
         */
        try {
            help.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), HelperPageActivity.class);
                    startActivity(intent);
                }
            });}
        catch (NullPointerException e){}

        /**
         * On Click listener for Drinkpage
         */
        try {
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textBoxHolder.setVisibility(View.INVISIBLE);
                    currentDrink = drinks.searchForDrink(input.getText().toString().toLowerCase().trim());

                    if (currentDrink!=null){
                        Intent goToDrinkPage = new Intent(getActivity(), DrinksUI.class);
                        Bundle b = new Bundle();
                        b.putString("key", input.getText().toString().toLowerCase().trim());
                        goToDrinkPage.putExtras(b);
                        input.setText("");
                        startActivity(goToDrinkPage);
                    }

                    else {

                        textBoxHolder.setVisibility(View.VISIBLE);
                    }
                }
            });} catch (NullPointerException e){}

    return v;
    }

}
