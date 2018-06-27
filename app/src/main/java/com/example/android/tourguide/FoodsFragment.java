package com.example.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodsFragment extends Fragment {
    public FoodsFragment() {
        // required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create a list of {@LINK TourEntry} items
        final ArrayList<TourEntry> tourEntries = new ArrayList<>();
        tourEntries.add(new TourEntry("Mint Cafe", "Casual Style", 4.5, 89, 44.9603180, -89.6279600, R.drawable.mint_cafe));
        tourEntries.add(new TourEntry("El Mezcal", "Mexican Dining", 5.0, 52, 44.9287060, -89.6547200, R.drawable.el_mezcal));
        tourEntries.add(new TourEntry("Thrive Foodery", "Casual Dining", 3.5, 32, 44.9736290, -89.6282170, R.drawable.thrive_foodery));
        tourEntries.add(new TourEntry("Lynn's Kitchen", "Family Dining", 5.0, 5, 44.9166886, -89.9654516, R.drawable.skillet));
        tourEntries.add(new TourEntry("McDonald's", "Fast food", 1.0, 0, 44.9593750, -89.6611570, R.drawable.mcdonalds_logo));
        tourEntries.add(new TourEntry("Hardees", "Fast food", 1.0, 82, 44.9571160, -89.6689540, R.drawable.burgerking_logo));


        // Create an {@link TourEntryAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        TourEntryAdapter adapter = new TourEntryAdapter(getActivity(), tourEntries, R.color.category_foods);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // list.xml layout file.
        ListView listView = rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link TourEntryAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link TourEntry} in the list.
        listView.setAdapter(adapter);

        // Set a click listener for when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link TourEntry} object at the given position the user clicked on
                TourEntry tourEntry = tourEntries.get(position);
                Intent mapIntent = new Intent(getContext(), MapsActivity.class);
                mapIntent.putExtra("latitude", tourEntry.getLatitude());
                mapIntent.putExtra("longitude", tourEntry.getLongitude());
                mapIntent.putExtra("title", tourEntry.getTitle());
                startActivity(mapIntent);
            }
        });

        return rootView;
    }

}

