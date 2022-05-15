package com.monstertechno.moderndashbord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Location_Activity extends AppCompatActivity {

    EditText locastart, endlocation;
    TextView test;
    Button go;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_location);

            locastart=findViewById(R.id.editTextstart);
            endlocation=findViewById(R.id.editTextend);
            test =findViewById(R.id.textViewtest);
            go=findViewById(R.id.buttongo);

            // Recevie  data.....................................
            Intent intent=getIntent();
            String flocation = intent.getStringExtra("Flocation");
            String  mylocation  = intent.getStringExtra("myloca");

            test.setText(flocation);

            // new adderess ..........................

            endlocation.setText(""+mylocation);
            locastart.setText(""+flocation);

            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String dest=   locastart.getText().toString().trim();
                    String    source   =   endlocation.getText().toString().trim();

                    if (source.equals("")&&  dest.equals("")){
                        Toast.makeText( getApplicationContext()," Enter both location ",Toast.LENGTH_LONG).show();
                    }else{
                        // Call methode
                        DisplayTrack(source,dest);
                    }
                }
            });
    }
    // Method : convert Latitude & longitude to address text
    protected String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }
    // location method track
    private void DisplayTrack(String source, String dest) {
            try {
                Uri uri =Uri.parse("https://www.google.co.in/maps/dir/"+ source + "/" + dest);

                Intent intent = new Intent(Intent.ACTION_VIEW,uri);

                intent.setPackage("com.google.android.apps.maps");

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            } catch (ActivityNotFoundException e) {

                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            }
    }
}