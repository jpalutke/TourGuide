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
        tourEntries.add(new TourEntry("Monk Botanical Gardens", "29-acre park with themed areas such as a meditation garden, extensive trails & a treehouse.",
                4.5, 61, 44.9881930, -89.6374250, R.drawable.monk_botanical_gardens));
        tourEntries.add(new TourEntry("Rib Mountain State Park", "Rib Mountain State Park is a 1,528-acre Wisconsin state park near the city of Wausau. The park includes a ski resort concession, Granite Peak Ski Area, a reservable amphitheather, and 15.1 miles of trails.",
                4.5, 399, 44.9215810, -89.6882210, R.drawable.rib_mountain_state_park));
        tourEntries.add(new TourEntry("Marathon Park", "Large park with mature pine trees & campsites, plus ballfields, skating rinks & a kids' train ride.",
                4.5, 205, 0, -0, R.drawable.marathon_park));
        tourEntries.add(new TourEntry("Sylvan Hill Park", "Popular place with a mountain biking park, cross-country ski trails & a tubing hill with tows.",
                4.5, 80, 44.9864320, -89.6059700, R.drawable.sylvan_park));
        tourEntries.add(new TourEntry("Isle of Ferns State Park", "Open play areas, hiking trails, popular place for wedding ceremony.",
                4.5, 16, 44.9517010, -89.6277950, R.drawable.isle_of_ferns));
        tourEntries.add(new TourEntry("Wausau Whitewater Park", "Nonprofit locale for competitive kayaking & canoeing, with slalom & freestyle runs.",
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
                mapIntent.putExtra("latitude", tourEntry.getLatitude());
                mapIntent.putExtra("longitude", tourEntry.getLongitude());
                mapIntent.putExtra("title", tourEntry.getTitle());
                startActivity(mapIntent);
            }
        });

        return rootView;
    }

}

