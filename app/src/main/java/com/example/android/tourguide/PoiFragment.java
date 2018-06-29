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

public class PoiFragment extends Fragment {
    public PoiFragment() {
        // required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create a list of {@link TourEntry} items
        final ArrayList<TourEntry> tourEntries = new ArrayList<>();
        tourEntries.add(new TourEntry(getString(R.string.colossal_title), getString(R.string.colossal_detail),
                4.5, 1, 44.9584100, -89.6281060, R.drawable.colossal_fossils_downtown_museum));
        tourEntries.add(new TourEntry(getString(R.string.granite_title), getString(R.string.granite_detail),
                4.0, 285, 44.9324360, -89.6809720, R.drawable.granite_peak_ski_area));
        tourEntries.add(new TourEntry(getString(R.string.yawkey_title), getString(R.string.yawkey_detail),
                5.0, 180, 44.9628330, -89.6134660, R.drawable.leigh_yawkey_woodson_art_museum));
        tourEntries.add(new TourEntry(getString(R.string.monk_title), getString(R.string.monk_detail),
                4.5, 61, 44.9881930, -89.6374250, R.drawable.monk_botanical_gardens));

        // Create an {@link TourEntryAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        TourEntryAdapter adapter = new TourEntryAdapter(getActivity(), tourEntries, R.color.category_poi);

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

