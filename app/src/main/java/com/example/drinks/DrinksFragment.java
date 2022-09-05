package com.example.drinks;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/** This class is the fragment for showing the the individual drinks pages.
 *  It sets up each page with a header, a picture, ingredients and some information about the drink.
 *  Furthermore there is a button one can click on to go to a webpage, where you can read more about the drink.
 *
 *
 *
 *
 */

public class DrinksFragment extends Fragment{
        private static ItemsDB itemsDB; // Static ITEMSDB
        private RecyclerView listThings; // Recycler List for Ingredients
        private ItemAdapter itemAdapter; // For creting lists within Recycler view
        private ArrayList<String> ingredients; // local list for ingredients
        private Drink currentDrink; // Current shown drink
        private TextView drinkHeader,description; // Drink header
        private Button learnMore; // Button which on click goes to website.
        private ImageView image;

    /** Method makes it possible to pass bundle with String (Drinkname) to the fragment, when switching orientation of screen.
     * @param drinkName the String name of the drink, which is used to get informations regarding drink.
     * @return a new drink fragment with a bundle with the passed argument.
     */
    public static DrinksFragment newInstance(String drinkName){
        Bundle args = new Bundle();
        args.putString("drinkname", drinkName);
        DrinksFragment df = new DrinksFragment();
        df.setArguments(args);
        return df;
    }

        /** Initializes variables on create
         *
         * @param savedInstanceState
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            itemsDB= ItemsDB.get(getActivity());
            currentDrink = itemsDB.searchForDrink(getArguments().getString("drinkname")); // Finds te current drink on behalf on search name.
            ingredients = currentDrink.convertIngredientsArrayToArrayList(); // Makes the ingredients into ArrayList
         }

        /** Initializes different things for the view. And connects UI with different underlying variables.
         * E.g. the header, the ingredients list, the url for the button and sets of on click listener
         * @param inflater
         * @param container
         * @param savedInstanceState
         * @return returns the view of the fragment
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) throws NullPointerException {
            final View v= inflater.inflate(R.layout.drink_fragment, container, false);
            drinkHeader = v.findViewById(R.id.DrinkHeader);
            drinkHeader.setText(currentDrink.getDrinkName().toUpperCase());
            listThings= v.findViewById(R.id.listIngredients);
            image = v.findViewById(R.id.image);
            listThings.setLayoutManager(new LinearLayoutManager(getActivity()));
            itemAdapter= new ItemAdapter();
            listThings.setAdapter(itemAdapter);
            learnMore = v.findViewById(R.id.button);
            description=v.findViewById(R.id.description_text);
            description.setText(currentDrink.getDescription());
            description.setMovementMethod(new ScrollingMovementMethod());


            /** Horrible switch statement. As a quick solution for adding pictures on some of the "drinks" in the file.
             * If there was more time available then it should be incorporated that each drink had stored a picture as a "blob" in the database.
             * The current solution in limited and doesn't have a great performance in terms of additional running time.
             *
             */
            switch (currentDrink.getDrinkName().toLowerCase()) {
                case "dark and stormy":
                    image.setImageResource(R.drawable.dark_and_stormy);
                    break;
                case "spritz veneziano":
                    image.setImageResource(R.drawable.spritz_veneziano);
                    break;
                case "rum and coke":
                    image.setImageResource(R.drawable.rum_and_coke);
                    break;
                case "daiquiri":
                    image.setImageResource(R.drawable.daiquiri);
                    break;
                case "margarita":
                    image.setImageResource(R.drawable.margarita);
                    break;
                case "piña colada":
                    image.setImageResource(R.drawable.pina_colada);
                    break;
                case "espresso martini":
                    image.setImageResource(R.drawable.espresso_martini);
                    break;
                case "cosmopolitan":
                    image.setImageResource(R.drawable.cosmopolitan);
                    break;
                case "tequila sunrise":
                    image.setImageResource(R.drawable.tequila_sunrise);
                    break;
                case "mojito":
                    image.setImageResource(R.drawable.mojito);
                    break;
                case "white russian":
                    image.setImageResource(R.drawable.white_russian);
                    break;
                case "irish coffee":
                    image.setImageResource(R.drawable.irish_coffee);
                    break;
                case "long island iced tea":
                    image.setImageResource(R.drawable.long_island_iced_tea);
                    break;
                case "whiskey sour":
                    image.setImageResource(R.drawable.whiskey_sour);
                    break;
                case "tom collins":
                    image.setImageResource(R.drawable.tom_collins);
                    break;
                case "pisco sour":
                    image.setImageResource(R.drawable.pisco_sour);
                    break;
                case "old fashioned":
                    image.setImageResource(R.drawable.old_fashioned);
                    break;
                case "hurricane":
                    image.setImageResource(R.drawable.hurricane);
                    break;
                case "mai tai":
                    image.setImageResource(R.drawable.mai_tai);
                    break;
                default:
                    image.setImageResource(R.drawable.placeholder_drink);
            }



            learnMore.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent browserIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(currentDrink.getURL()));
                    startActivity(browserIntent);
                }
            });

            return v;
        }


    /** Item holder sets up the individual row for the recycler view list format on the page.
     *
     */
        private class ItemHolder extends RecyclerView.ViewHolder {
            private TextView mWhatTextView, mNoView;

        /** Initializes the class
         *
         * @param itemView
         */
        public ItemHolder(View itemView) {
                super(itemView);
                mNoView= itemView.findViewById(R.id.item_no);
                mWhatTextView= itemView.findViewById(R.id.item_what);

                // itemView.setOnClickListener(this);
            }
            /** binds the number of the row in the array as well as ingredient
             *
             * @param ingredient
             * @param position
             */
            public void bind(String ingredient, int position){
                mNoView.setText(" "+position+" ");
                mWhatTextView.setText(ingredient);

            }



        }

    /** Item adapter sets up the actual list for the recyclerview
     *
     */
    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

            @Override
            public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater= LayoutInflater.from(getActivity());
                View v= layoutInflater.inflate(R.layout.one_row, parent, false);
                return new ItemHolder(v);
            }

            @Override
            public void onBindViewHolder(ItemHolder holder, int position) {

                holder.bind(ingredients.get(position), position+1);
            }

            @Override
            public int getItemCount(){ return ingredients.size(); }
        }

    }

