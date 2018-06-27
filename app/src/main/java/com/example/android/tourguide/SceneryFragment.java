package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class SceneryFragment extends Fragment {
    public SceneryFragment() {
        // required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create a list of {@link TourEntry} items
        final ArrayList<TourEntry> tourEntries = new ArrayList<>();
        tourEntries.add(new TourEntry("", "", 0.0, 0, 0, 0, R.drawable.image_1));
        tourEntries.add(new TourEntry("", "", 0.0, 0, 0, 0, R.drawable.image_2));
        tourEntries.add(new TourEntry("", "", 0.0, 0, 0, 0, R.drawable.image_3));
        tourEntries.add(new TourEntry("", "", 0.0, 0, 0, 0, R.drawable.image_4));
        tourEntries.add(new TourEntry("", "", 0.0, 0, 0, 0, R.drawable.image_5));
        tourEntries.add(new TourEntry("", "", 0.0, 0, 0, 0, R.drawable.image_7));
        tourEntries.add(new TourEntry("", "", 0.0, 0, 0, 0, R.drawable.image_8));

        // Create an {@link TourEntryAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        TourEntryAdapter adapter = new TourEntryAdapter(getActivity(), tourEntries, R.color.category_scenery);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // list.xml layout file.
        ListView listView = rootView.findViewById(R.id.list);
        listView.setDividerHeight(10);

        // Make the {@link ListView} use the {@link TourEntryAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link TourEntry} in the list.
        listView.setAdapter(adapter);

        return rootView;
    }

}

