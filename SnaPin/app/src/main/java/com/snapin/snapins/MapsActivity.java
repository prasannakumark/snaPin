package com.snapin.snapins;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    LatLng TO_LOCATION;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        try {
            mMap.setMyLocationEnabled(true);
        }catch (SecurityException ex){}


        LatLng TO_DESTINATION = new LatLng(13.8600805, 77.5164413);


        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, new android.location.LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    TO_LOCATION = new LatLng(location.getLongitude(), location.getLatitude());
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
            Location myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            double longitude = myLocation.getLongitude();
            double latitude = myLocation.getLatitude();
            TO_LOCATION = new LatLng(longitude, latitude);


        }catch (SecurityException ex){}


        mMap.addMarker(new MarkerOptions().position(TO_DESTINATION).title("Destination Title")
                .snippet("Destination Description"));
        mMap.addMarker(new MarkerOptions().position(TO_LOCATION).title("Location Title")
                .snippet("Location Description"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TO_LOCATION, 40));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        MarkerOptions markerOptions = new MarkerOptions();

        ArrayList<LatLng> directionPoint = new ArrayList<>();
        directionPoint.add(new LatLng(13.8600805,77.5164413));
        directionPoint.add(new LatLng(13.7804434,77.47773180000002));
        directionPoint.add(new LatLng(13.8185378,77.49887529999999));
        PolylineOptions rectLine = new PolylineOptions().width(5)
                .color(Color.RED);

        for (int i = 0; i < directionPoint.size(); i++) {
            rectLine.add(directionPoint.get(i));
        }
        // Adding route on the map
        mMap.addPolyline(rectLine);
        markerOptions.position(TO_DESTINATION);
        markerOptions.position(TO_LOCATION);
        markerOptions.draggable(true);
        mMap.addMarker(markerOptions);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(TO_DESTINATION).zoom(15.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory
                .newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

    }
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

        }
    };
}
