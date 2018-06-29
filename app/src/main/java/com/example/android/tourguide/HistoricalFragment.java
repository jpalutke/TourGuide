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

public class HistoricalFragment extends Fragment {
    public HistoricalFragment() {
        // required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create a list of {@LINK TourEntry} items
        final ArrayList<TourEntry> tourEntries = new ArrayList<>();
        //noinspection SpellCheckingInspection
        tourEntries.add(new TourEntry(getString(R.string.wausau_railroad_depot_title), getString(R.string.wausau_railroad_depot_detail),
                0.0, 0, 44.9618700, -89.6199040, R.drawable.wausau_railroad_depot,
                getString(R.string.wausau_railroad_depot_details_story)));

        //noinspection SpellCheckingInspection
        tourEntries.add(new TourEntry(getString(R.string.ishorgen_title), getString(R.string.ishorgen_detail),
                0.0, 0, 44.9571030, -89.6517970, R.drawable.is_horgen,
                getString(R.string.ishorgen_details_story)));

        tourEntries.add(new TourEntry(getString(R.string.woodson_art_title), getString(R.string.woodson_art_detail),
                0.0, 0, 44.9628330, -89.6134660, R.drawable.woodson_art_museum,
                getString(R.string.woodson_art_details_story)));

        //noinspection SpellCheckingInspection,SpellCheckingInspection,SpellCheckingInspection,SpellCheckingInspection,SpellCheckingInspection
        tourEntries.add(new TourEntry(getString(R.string.woodson_history_title), getString(R.string.woodson_history_detail),
                0.0, 0, 44.9636520, -89.6257590, R.drawable.woodson_history_center,
                getString(R.string.woodson_history_details_story)));

        // Create an {@link TourEntryAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        TourEntryAdapter adapter = new TourEntryAdapter(getActivity(), tourEntries, R.color.category_historical);

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
                Intent detailIntent = new Intent(getContext(), DetailedViewActivity.class);
                detailIntent.putExtra(getString(R.string.latitude), tourEntry.getLatitude());
                detailIntent.putExtra(getString(R.string.longitude), tourEntry.getLongitude());
                detailIntent.putExtra(getString(R.string.title), tourEntry.getTitle());
                detailIntent.putExtra(getString(R.string.details), tourEntry.getDetails());
                detailIntent.putExtra(getString(R.string.story), tourEntry.getStory());
                detailIntent.putExtra(getString(R.string.imageResourceID), tourEntry.getImageResourceID());
                startActivity(detailIntent);
            }
        });

        return rootView;
    }

}

