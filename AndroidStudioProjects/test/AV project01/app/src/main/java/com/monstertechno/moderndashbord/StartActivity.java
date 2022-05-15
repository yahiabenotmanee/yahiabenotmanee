package com.monstertechno.moderndashbord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    private FirebaseAuth mauth;
    Button start;
    //EditText editText,editText1;
    ImageView vehcules ;
    TextView textView, signintext;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mauth= FirebaseAuth.getInstance();
        //status bar color
        getWindow().setStatusBarColor(ContextCompat.getColor(StartActivity.this,R.color.G_black));

        start=findViewById(R.id.button);
        textView=findViewById(R.id.textView3);
        signintext=findViewById(R.id.textViewsignin);
        signintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

       // vehcules=findViewById(R.id.gifImageVEHCILES);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                if(mauth.getCurrentUser() != null){
//                    startActivity(new Intent(StartActivity.this,LoginActivity.class));
//                }else{
//                    startActivity(new Intent(StartActivity.this,MainActivity.class));
//                }
//                finish();
            }
        }, 3000);

        // Dih home location
        //29.482442
        //-1.499345
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
//                if(mauth.getCurrentUser() != null){
//                    startActivity(new Intent(StartActivity.this,LoginActivity.class));
//                }else{
//                    startActivity(new Intent(StartActivity.this,LoginActivity.class));
//                }
//                finish();
            }

        });
    }
}
