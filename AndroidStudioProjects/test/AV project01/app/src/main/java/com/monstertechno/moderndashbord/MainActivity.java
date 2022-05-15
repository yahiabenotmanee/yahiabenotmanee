package com.monstertechno.moderndashbord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
ImageView imageViewlocation  ,  imageViewgenral   ,  imageViewalarm , imageViewNOTES;

    LinearLayout linearLayoutlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_main);

        imageViewlocation=findViewById(R.id.locationtap);
        imageViewgenral=findViewById(R.id.genralbutton);
        imageViewalarm=findViewById(R.id.AlarmCard);
        imageViewNOTES=findViewById(R.id.notes);


        linearLayoutlocation=findViewById(R.id.linearLayoutlocation);

        //Enter to notes activity
        imageViewNOTES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                startActivity(intent);
            }
        });
        //Enter to alarm activity
        imageViewalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Alarm_Activity.class);
                startActivity(intent);
            }
        });
        //Enter to general activity
        imageViewgenral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //Enter to location activity
        imageViewlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,GLocation_Activity.class);
                startActivity(intent);
            }
        });
    }
}