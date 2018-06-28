package com.example.android.tourguide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.String.format;

class TourEntryAdapter extends ArrayAdapter<TourEntry> {

    private final int colorResourceId;

    /**
     * Create a new {@link TourEntryAdapter} object.
     *
     * @param context         is the current context that the adapter is being created in.
     * @param tourEntries     is the list of {@link TourEntry}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of TourEntries
     */
    public TourEntryAdapter(Activity context, ArrayList<TourEntry> tourEntries, int colorResourceId) {
        super(context, 0, tourEntries);
        this.colorResourceId = colorResourceId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the recycler_view of data that should be displayed in the
     *                    recycler_view item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        boolean imageOnly;
        View[] star = new View[5];

        // Reusing view? If not, inflate view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        /* Get the {@link TourEntry} object located at this position in the recycler_view */
        TourEntry currentTourEntry = getItem(position);

        // if it isn't null then place values into the view
        if (currentTourEntry == null)
            throw new AssertionError();
        else {
            // if title and details are blank ("") then this is an "image only" entry
            imageOnly = currentTourEntry.getTitle().equals("") && currentTourEntry.getDetails().equals("");
            if (imageOnly) {
                // Hide everything except the ImageView
                listItemView.findViewById(R.id.list_item_text_container).setVisibility(View.GONE);
                listItemView.findViewById(R.id.list_item_map_pin_container).setVisibility(View.GONE);
                listItemView.findViewById(R.id.list_item_image_view).setVisibility(View.GONE);

                // show the desired image
                AppCompatImageView fullImageView = listItemView.findViewById(R.id.list_item_image_only_view_image);
                fullImageView.setImageResource(currentTourEntry.getImageResourceID());
                listItemView.findViewById(R.id.list_item_image_only_view).setVisibility(View.VISIBLE);

            } else {
                // Display the item title
                ((TextView) listItemView.findViewById(R.id.list_item_title_text_view)).setText(currentTourEntry.getTitle());

                // Display the item detail
                ((TextView) listItemView.findViewById(R.id.list_item_details_text_view)).setText(currentTourEntry.getDetails());

                star[0] = listItemView.findViewById(R.id.star_1);
                star[1] = listItemView.findViewById(R.id.star_2);
                star[2] = listItemView.findViewById(R.id.star_3);
                star[3] = listItemView.findViewById(R.id.star_4);
                star[4] = listItemView.findViewById(R.id.star_5);

                // Prepare all stars
                int count;

                // Make all stars' visibility GONE and set their image to a FILLED star
                for (count = 0; count <= 4; count++) {
                    star[count].setVisibility(View.GONE);
                    star[count].setBackgroundResource(R.drawable.ic_star_filled_24dp);
                }

                // turn on visibility of all full stars
                for (count = 0; count < currentTourEntry.getStars(); count++) {
                    star[count].setVisibility(View.VISIBLE);
                }

                // turn on the half star if one is required
                // YES, we know we already set it to a full star, but this is faster
                if (currentTourEntry.getStars() % 1 != 0) {
                    star[count - 1].setVisibility(View.VISIBLE);
                    star[count - 1].setBackgroundResource(R.drawable.ic_star_half_24dp);
                }

                // show review count
                TextView tv = listItemView.findViewById(R.id.review_count);
                if (currentTourEntry.getReviewCount() > 0) {
                    tv.setText(String.format("%s reviews", format("%d", currentTourEntry.getReviewCount())));
                    tv.setVisibility(View.VISIBLE);
                } else
                    tv.setVisibility(View.GONE);

                // if an image for the item was specified on creation,
                //  show it, otherwise hide the ImageView.
                ImageView imageView = listItemView.findViewById(R.id.list_item_image_view);
                if (currentTourEntry.hasImage()) {
                    imageView.setImageResource(currentTourEntry.getImageResourceID());
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
                if (currentTourEntry.isMappable() && currentTourEntry.getStory().equals(""))
                    listItemView.findViewById(R.id.list_item_map_pin_container).setVisibility(View.VISIBLE);
                else
                    listItemView.findViewById(R.id.list_item_map_pin_container).setVisibility(View.GONE);
            }
        }


        // Set the background color of the text container View
        listItemView.findViewById(R.id.list_item_background).setBackgroundColor(ContextCompat.getColor(getContext(), colorResourceId));

        // Return the view so that it can be shown in the ListView
        return listItemView;
    }

}