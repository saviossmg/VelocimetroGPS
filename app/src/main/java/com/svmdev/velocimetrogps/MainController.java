package com.svmdev.velocimetrogps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.svmdev.velocimetrogps.GPSHelper.CLocation;
import com.svmdev.velocimetrogps.GPSHelper.GPSListener;


/**
 * Desenvolvido por Sávio Martins Valentim em 22/07/2019.
 */
public class MainController implements GPSListener {

    private final MainActivity mainActivity;

    private LocationManager locationManager;

    public MainController(MainActivity view) {
        this.mainActivity = view;
        this.locationManager = (LocationManager) view.getSystemService(Context.LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    public void initGps() {
        if (this.dontHavePermissions()) {
            ActivityCompat.requestPermissions(
                    mainActivity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                    1
            );
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }


    private void updateSpeed(CLocation location) {
        float nCurrentSpeed = 0;

        if(location != null)
        {
            nCurrentSpeed = location.getSpeed();
        }

        String finalSpeed = String.format(java.util.Locale.US,"%.2f", nCurrentSpeed);

        mainActivity.txtVelocidade().setText(finalSpeed);
    }

    private boolean dontHavePermissions(){
        return ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null)
        {
            CLocation newLocation = new CLocation(location);
            this.updateSpeed(newLocation);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onGpsStatusChanged(int event) {

    }
}