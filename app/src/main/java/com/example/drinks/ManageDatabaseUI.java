package com.example.drinks;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/** Class adds functionality to the fragment of Manage Database.
 * Where the user can add and remove drinks from or to the SQLite datebase
 *
 */
public class ManageDatabaseUI extends Fragment {
    private static ItemsDB drinks;
    private EditText whatDrink, drinkURL, drinkIngredients, descriptionAndRemove;
    private TextView removeAndDescriptionText, textBox;
    private Button addNewDrink, removeDrink, confirm;
    private LinearLayout everything, topThreeDrinkQuestions;
    private boolean removeOrAddButton; // False = Remove, True = Add

    /** Initializes variables on create
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drinks = ItemsDB.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws NullPointerException {
        final View v = inflater.inflate(R.layout.fragment_ui, container, false);
       // general = v.findViewById(R.id.GeneralInformation);
        everything = v.findViewById(R.id.linearWithEverythingManageDb);
        topThreeDrinkQuestions = v.findViewById(R.id.top3QuestionsManageDB);
        whatDrink = v.findViewById(R.id.what_text);
        drinkURL = v.findViewById(R.id.where_text);
        drinkIngredients= v.findViewById(R.id.ingredients_text);
        descriptionAndRemove = v.findViewById(R.id.information_text_remove_text);
        removeAndDescriptionText = v.findViewById(R.id.text_remove_or_description);
        textBox = v.findViewById(R.id.confirm_add_remove_text);
        addNewDrink = v.findViewById(R.id.add_item);
        removeDrink = v.findViewById(R.id.remove_drink);
        confirm = v.findViewById(R.id.confirm_add_remove_button);
        everything.setVisibility(View.INVISIBLE);
        topThreeDrinkQuestions.setVisibility(View.INVISIBLE);

        removeDrink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               everything.setVisibility(View.VISIBLE);
                topThreeDrinkQuestions.setVisibility(View.INVISIBLE);
            removeAndDescriptionText.setText("What drink would you like to remove?");
            descriptionAndRemove.setHint("E.g. Piña Colada");
            removeOrAddButton = false;
            }
        });

        addNewDrink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                everything.setVisibility(View.VISIBLE);
                topThreeDrinkQuestions.setVisibility(View.VISIBLE);
                removeAndDescriptionText.setText("Add a description to the drink");
                descriptionAndRemove.setHint("E.g. Piña Colada means strained pineapple");
                removeOrAddButton = true;
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (removeOrAddButton){
                    String what = whatDrink.getText().toString().trim();
                    String where = drinkURL.getText().toString().trim();
                    String ingredients = drinkIngredients.getText().toString().trim();
                    String information = descriptionAndRemove.getText().toString().trim();

                    if (what.length()<1 && where.length()<1){
                        Toast.makeText(getActivity(),"You're kinda missing what drink you want to add... As well as the URL... ",Toast.LENGTH_LONG).show();
                    }
                    else if (what.length()<1){
                        Toast.makeText(getActivity(),"You're missing what drink you're talking about... ",Toast.LENGTH_LONG).show();
                    }
                    else if (where.length()<1){
                        Toast.makeText(getActivity(),"You have to tell me what the URL is...",Toast.LENGTH_LONG).show();
                    }
                    else if (ingredients.length()<1){

                        Toast.makeText(getActivity(),"You're missing the drink's ingredients... ",Toast.LENGTH_LONG).show();
                    }
                    else if (information.length()<1){
                        Toast.makeText(getActivity(),"You're missing a description of the drink you're talking about...",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getActivity(), drinks.addDrink(what, ingredients, where, information), Toast.LENGTH_LONG).show();
                        whatDrink.getText().clear();
                        drinkURL.getText().clear();
                        descriptionAndRemove.getText().clear();
                        drinkIngredients.getText().clear();
                        everything.setVisibility(View.INVISIBLE);
                        topThreeDrinkQuestions.setVisibility(View.INVISIBLE);
                    }
                } else if(!removeOrAddButton){
                    if (!descriptionAndRemove.getText().toString().trim().isEmpty()) {
                        drinks.removeItem(descriptionAndRemove.getText().toString());
                        Toast.makeText(getActivity(), "Removed ", Toast.LENGTH_SHORT).show();
                        descriptionAndRemove.getText().clear();
                        everything.setVisibility(View.INVISIBLE);
                        topThreeDrinkQuestions.setVisibility(View.INVISIBLE);
                    }else Toast.makeText(getActivity(), "Which drink do you wish to remove?", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
     }
    }
