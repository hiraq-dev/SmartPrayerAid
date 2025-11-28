package com.qaiser.hyra.namazapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NamazMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namaz_menu);
    }


    private void initialize() {

        // Start receiver with the name StartupReceiver_Manual_Start
        // Check AndroidManifest.xml file
        getBaseContext().getApplicationContext().sendBroadcast(
                new Intent("StartupReceiver_Manual_Start"));
    }
    public void startService (View v){
        initialize();
        Intent i=new Intent(this,VoiceService.class);
        startService(i);
    }

    public void stopService (View v){
        Intent i=new Intent(this,VoiceService.class);
        stopService(i);
    }
}
