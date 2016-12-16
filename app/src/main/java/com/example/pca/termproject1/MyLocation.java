package com.example.pca.termproject1;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pca on 2016-12-16.
 */

public class MyLocation extends AppCompatActivity implements LocationListener{
    LocationManager locationManager;

    public MyLocation(){
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    }
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
