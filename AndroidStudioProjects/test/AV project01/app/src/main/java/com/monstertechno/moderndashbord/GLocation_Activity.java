package com.monstertechno.moderndashbord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class GLocation_Activity extends AppCompatActivity {

    Timer timergif, timeractivity;
    ConstraintLayout home;
    public String S1,S2,X;
    public static String Messagex ;

    //  Intia .... of Message methode resive variabels
    /////////////////////////////////////////////////////////////////////////////////////////////
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0;
    private static final String SMS_RECEIVED = " android.provider.Telephony.SMS_RECEIVED";
    TextView mylocation00, mylocation01;
    TextView messtext,numbertext;
    ////////////////////////////////////////////////////////////////////////////////////////////
    MessageRecive recive = new MessageRecive() {
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            //// msg ki yl7eg ..................
            //show message
          LONGITUDEe.setText(Messagex);
            LATITUDEe.setText(msg);

            messtext.setText(Messagex);
            numbertext.setText(phoneNo);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(recive, new IntentFilter(SMS_RECEIVED));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(recive);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Button getlocation;
    EditText LONGITUDEe, LATITUDEe;
    ImageView mapgif, iconegif;
    String c1, c2;
    ///////////////////////////////////////////////////////////////     Main     /////////////////////////////////////////////////////////

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glocation);

        mylocation00 = findViewById(R.id.textView5location);
        mylocation01 = findViewById(R.id.textView5);

        messtext=findViewById(R.id.textViewmessage);
        numbertext=findViewById(R.id.textView7);

        // inti fused
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        /////////////////// to acivate pesrmession
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // rabt
        home = findViewById(R.id.home);
        getlocation = findViewById(R.id.button3getlocation);
        LATITUDEe = findViewById(R.id.editTextLATITUDE);
        LONGITUDEe = findViewById(R.id.editTextLONGITUDE);
        mapgif = findViewById(R.id.gifImagemap);
        iconegif = findViewById(R.id.giflocationicon);

        //LONGITUDEe.setText(msgrsv);
        // get location when click button
        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////////////////////////////////////// location permission for me
                if (ActivityCompat.checkSelfPermission(GLocation_Activity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();

                } else {
                    ActivityCompat.requestPermissions(GLocation_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
////////////////////////////////////////////////////////////////////////////////////////////

                c1 = LATITUDEe.getText().toString();
                c2 = LONGITUDEe.getText().toString();

                double str1 = Double.parseDouble(c1);
                double str = Double.parseDouble(c2);

            String c = getCompleteAddressString(str,str1);

                // show location address text

                ///// time befoer get location
                timeractivity = new Timer();
                timeractivity.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        /// pass data to location activty
                        Intent intent = new Intent(GLocation_Activity.this, Location_Activity.class);
                        intent.putExtra("Flocation", X);
                        intent.putExtra("myloca",c);

                        startActivity(intent);
                    }
                }, 3000);
            }
        });

        // lwa9t nta3 google map
        timergif = new Timer();
        timergif.schedule(new TimerTask() {
            @Override
            public void run() {
               mapgif.setVisibility(View.INVISIBLE);
                home.setBackgroundColor(ContextCompat.getColor(GLocation_Activity.this, R.color.WB_color));
                iconegif.setVisibility(View.VISIBLE);
                getlocation.setVisibility(View.VISIBLE);

                LATITUDEe.setVisibility(View.VISIBLE);
                LONGITUDEe.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }


    //CONVIRT cordinat method
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(GLocation_Activity.this, Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        //set latitude on textvew
                        mylocation00.setText(""+ addresses.get(0).getLatitude());
                        mylocation01.setText(""+ addresses.get(0).getLongitude());
                        S1 = mylocation00.getText().toString();
                        S2 = mylocation01.getText().toString();

                        double str3 = Double.parseDouble(S2);
                        double str2 = Double.parseDouble(S1);

                        X= getCompleteAddressString(str2,str3);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}





