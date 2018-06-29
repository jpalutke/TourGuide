package com.example.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        int imageResourceID;

        String titleString;
        String detailsString;
        String storyString;

        // get values from calling Intent
        Intent intent = getIntent();
        imageResourceID = intent.getIntExtra(getString(R.string.imageResourceID), 0);
        titleString = intent.getStringExtra(getString(R.string.title));
        detailsString = intent.getStringExtra(getString(R.string.details));
        storyString = intent.getStringExtra(getString(R.string.story));

        View pageView = findViewById(R.id.detail_page_view);
        pageView.setBackgroundColor(getResources().getColor(R.color.category_historical));

        ImageView detailImageView = findViewById(R.id.detail_image_view);
        detailImageView.setImageResource(imageResourceID);

        TextView title = findViewById(R.id.detail_title_text);
        title.setText(titleString);

        TextView details = findViewById(R.id.detail_details);
        details.setText(detailsString);

        WebView story = findViewById(R.id.detail_story);
        story.setBackgroundColor(getResources().getColor(R.color.category_historical));
        storyString = getString(R.string.html_header) + storyString + getString(R.string.html_footer);
        story.loadData(storyString, "text/html", "utf-8");
    }
}
