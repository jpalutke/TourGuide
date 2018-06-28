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
        tourEntries.add(new TourEntry("Wausau Railroad Depot", "720 Grant Street Wausau, WI 54403",
                0.0, 0, 44.9618700, -89.6199040, R.drawable.wausau_railroad_depot,
                "This landmark is a highly recognized symbol of Wausau. Wausau Insurance Companies used the "
                        + "depot as its trademark as early as 1954. Finding this depot while you’re in the area is not as simple "
                        + "as you might think because, in truth, Wausau has three depots.</p><p align=\"justify\">The depot in the Wausau Insurance "
                        + "trademark is modeled after the former Milwaukee Railroad facility located at 720 Grant Street. A "
                        + "faithful reproduction of this famous building was built by Wausau Insurance and serves as a meeting "
                        + "facility near their corporate headquarters at 1800 Westwood Drive.</p><p align=\"justify\">The third depot once served "
                        + "passengers of the Chicago Northwestern Railroad. Now home to several businesses, it is located on "
                        + "Washington Street in downtown Wausau along the Wisconsin River."));

        //noinspection SpellCheckingInspection
        tourEntries.add(new TourEntry("I.S. Horgen Antique Farm Machinery Museum", "1201 Stewart Avenue Wausau, WI 54401",
                0.0, 0, 44.9571030, -89.6517970, R.drawable.is_horgen,
                "A fascinating display of antique farm equipment and tools including vintage dairy artifacts, large wagons, "
                        + "carriages and more. The collection is well organized and labeled for easy viewing. A special toy farm and a toy farm "
                        + "tractor collection is on display. Admission is free and the museum is handicapped accessible."));

        tourEntries.add(new TourEntry("Woodson Art Museum", "700 North Twelfth Street Wausau, WI 54403",
                0.0, 0, 44.9628330, -89.6134660, R.drawable.woodson_art_museum,
                "Every September through the second weekend in November, 110 artists from the world over, display their creations "
                        + "of birds. The museum is known for it's permanent collection of bird art that graces the gallery all year long."));

        //noinspection SpellCheckingInspection,SpellCheckingInspection,SpellCheckingInspection,SpellCheckingInspection,SpellCheckingInspection
        tourEntries.add(new TourEntry("Woodson History Center", "410 McIndoe Street Wausau, WI 54403",
                0.0, 0, 44.9636520, -89.6257590, R.drawable.woodson_history_center,
                "This lovely brick house was built in 1914 for the Yawkey’s daughter Leigh and her husband Aytchmonde P. Woodson. "
                        + "This house was also designed by George W. Maher.</p><p align=\"justify\">After both of the Yawkeys had died, the Woodsons "
                        + "moved to Highland Park Boulevard and sold their house to the Immanuel Baptist congregation. The church added a bell "
                        + "tower and a sanctuary to the back of the house in 1956.</p><p align=\"justify\">The Historical Society purchased the Woodson "
                        + "House in 1995, and today it contains the library and archives, artifacts and exhibits.</p><p align=\"justify\">The old Woodson "
                        + "mansion has a haunted past. Staff have reported the sound of a telephone ringing in a room that has no phone. Phantom footsteps "
                        + "can be heard coming down the upstairs hallway and descending the main staircase. Strange odors have been reported in an upstairs "
                        + "room, and the door alarm often sounds by itself."));

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
                detailIntent.putExtra("latitude", tourEntry.getLatitude());
                detailIntent.putExtra("longitude", tourEntry.getLongitude());
                detailIntent.putExtra("title", tourEntry.getTitle());
                detailIntent.putExtra("details", tourEntry.getDetails());
                detailIntent.putExtra("story", tourEntry.getStory());
                detailIntent.putExtra("imageResourceID", tourEntry.getImageResourceID());
                startActivity(detailIntent);
            }
        });

        return rootView;
    }

}

