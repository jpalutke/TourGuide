package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * {@link MapsActivity}
 * <p>
 * When launching the intent, set the following extras:
 * extraDouble latitude
 * extraDouble longitude
 * extraString title
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private double latitude;
    private double longitude;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude", 0);
        title = getIntent().getStringExtra("title");
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map when it is available. If Google Maps is not
     * already installed, the user will be prompted to install it.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add a marker to the coordinates specified when the Intent
        // was created, move the camera and then zoom to street level
        LatLng location = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(location).title(title));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f));
    }
}
