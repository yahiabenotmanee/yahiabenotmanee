package com.monstertechno.moderndashbord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Alarm_Activity extends AppCompatActivity {
    ImageView alarmgif;
     Timer timergif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmgif=findViewById(R.id.alarmGIF);
        timergif = new Timer();
        timergif.schedule(new TimerTask() {
            @Override
            public void run() {
                alarmgif.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }
}