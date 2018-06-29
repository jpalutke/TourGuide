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

public class ParksFragment extends Fragment {
    public ParksFragment() {
        // required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create a list of {@link TourEntry} items
        final ArrayList<TourEntry> tourEntries = new ArrayList<>();
        tourEntries.add(new TourEntry(getString(R.string.monk_title), getString(R.string.monk_detail),
                4.5, 61, 44.9881930, -89.6374250, R.drawable.monk_botanical_gardens));
        tourEntries.add(new TourEntry(getString(R.string.rib_mountain_title), getString(R.string.rib_mountain_detail),
                4.5, 399, 44.9215810, -89.6882210, R.drawable.rib_mountain_state_park));
        tourEntries.add(new TourEntry(getString(R.string.marathon_park_title), getString(R.string.marathon_park_detail),
                4.5, 205, 44.9571030, -89.6517970, R.drawable.marathon_park));
        tourEntries.add(new TourEntry(getString(R.string.sylvan_title), getString(R.string.sylvan_detail),
                4.5, 80, 44.9864320, -89.6059700, R.drawable.sylvan_park));
        tourEntries.add(new TourEntry(getString(R.string.ferns_title), getString(R.string.ferns_detail),
                4.5, 16, 44.9517010, -89.6277950, R.drawable.isle_of_ferns));
        tourEntries.add(new TourEntry(getString(R.string.whitewater_title), getString(R.string.whitewater_detail),
                5.0, 50, 44.9580040, -89.6321500, R.drawable.wausau_whitewater_park));

        // Create an {@link TourEntryAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        TourEntryAdapter adapter = new TourEntryAdapter(getActivity(), tourEntries, R.color.category_parks);

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
                mapIntent.putExtra(getString(R.string.latitude), tourEntry.getLatitude());
                mapIntent.putExtra(getString(R.string.longitude), tourEntry.getLongitude());
                mapIntent.putExtra(getString(R.string.title), tourEntry.getTitle());
                startActivity(mapIntent);
            }
        });

        return rootView;
    }

}

