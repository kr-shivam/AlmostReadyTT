package com.knight.myapplication.categories;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.knight.myapplication.MainActivity;
import com.knight.myapplication.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

/**
 * Created by knight on 6/15/2017.
 */

public class LocationGrabActivity extends Activity {


    private static final int MY_PERMISSION_FINE_LOCATION = 101;
    private static final int PLACE_PICKER_REQUEST = 1;
    Button button;
    TextView placeNameText;
    TextView placeAddressText;
    TextView tvLocation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationgrab);

        requestPermission();

        placeNameText = (TextView) findViewById(R.id.tvTextView);
        placeAddressText = (TextView) findViewById(R.id.tvPlaceAddress);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(LocationGrabActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            } else {
                ActivityCompat.requestPermissions(LocationGrabActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        } else {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try {
                tvLocation.setText(hereLocation(location.getLatitude(), location.getLongitude()));

            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(LocationGrabActivity.this, "Not Found!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(LocationGrabActivity.this, data);
                placeNameText.setText(place.getName());
                placeAddressText.setText(place.getAddress());
            }
        }
    }

    public String hereLocation(double lat, double lon){
        String ourCity = "";

        Geocoder geocoder = new Geocoder(LocationGrabActivity.this, Locale.getDefault());
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocation(lat, lon, 1);
            if (addressList.size() >0 ){
                ourCity = addressList.get(0).getLocality();
            }

        } catch (Exception e){
            e.printStackTrace();
        } return ourCity;
    }
}
