package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Geolocation extends AppCompatActivity  {
    Button btgeo;
    TextView textView1, textView2, textView3, textView4, textView5;
    public FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocation);
        btgeo = findViewById(R.id.btgeo);
        textView1 = findViewById(R.id.txtview);
        textView2 = findViewById(R.id.txtview2);
        textView3 = findViewById(R.id.txtview3);
        textView4 = findViewById(R.id.txtview4);
        textView5 = findViewById(R.id.txtview5);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btgeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(Geolocation.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(Geolocation.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            44);
                }
            }
        });
    }

    private void getLocation() {


        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {

                    try {
                        Geocoder geocoder = new Geocoder(Geolocation.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        textView1.setText(Html.fromHtml("<font color='#6200EE'><b>Latitude:</bb><br></font>" + addresses.get(0).getLatitude()));
                        textView2.setText(Html.fromHtml("<font color='#6200EE'><b>Longitude:</bb><br></font>" + addresses.get(0).getLongitude()));
                        textView3.setText(Html.fromHtml("<font color='#6200EE'><b>Country Name:</bb><br></font>" + addresses.get(0).getCountryName()));
                        textView4.setText(Html.fromHtml("<font color='#6200EE'><b>Locality:</bb><br></font>" + addresses.get(0).getLocality()));
                        textView5.setText(Html.fromHtml("<font color='#6200EE'><b>Address:</bb><br></font>" + addresses.get(0).getAddressLine(0)));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });
    }

}