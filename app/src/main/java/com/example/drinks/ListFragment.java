package com.example.drinks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/** List Fragment it setup the List UI and apply the parameters to it.
 *
 */

public class ListFragment extends Fragment implements Observer {
    private static ItemsDB itemsDB;
    private RecyclerView listThings;
    private ItemAdapter itemAdapter;
    private ArrayList<Drink> localDB;

    /** Updates local list using the observer
     *
     * @param observable
     * @param data
     */
    public void update(Observable observable, Object data) {
        localDB= itemsDB.listItems();
        itemAdapter.notifyDataSetChanged();
    }

    /** Initializes variables on create
     *
     * @param savedInstanceState
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemsDB= ItemsDB.get(getActivity());
        itemsDB.addObserver(this);
        localDB=itemsDB.listItems();
    }

    /** Initialises variables on the creation of the UI
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v= inflater.inflate(R.layout.fragment_list, container, false);
        listThings= v.findViewById(R.id.listItems);
        listThings.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemAdapter= new ItemAdapter();
        listThings.setAdapter(itemAdapter);
        return v;
    }

    /** Item holder that creates the setup for the list
     *
     */
    private class ItemHolder extends RecyclerView.ViewHolder {
        private TextView mWhatTextView, mNoView;

        public ItemHolder(View itemView) {
            super(itemView);
            mNoView= itemView.findViewById(R.id.item_no);
            mWhatTextView= itemView.findViewById(R.id.item_what);
        }

        /** Binds together the Drink and the position in the ArrayList for a row in the recycler view.
         *
         * @param drink
         * @param position
         */
        public void bind(Drink drink, int position){
            mNoView.setText(" "+position+" ");
            mWhatTextView.setText(drink.getDrinkName());

        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(getActivity());
            View v= layoutInflater.inflate(R.layout.one_row, parent, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder( ItemHolder holder, int position) {
            Drink drink= localDB.get(position);
            holder.bind(drink, (position+1));
        }

        @Override
        public int getItemCount(){ return localDB.size(); }
    }

}