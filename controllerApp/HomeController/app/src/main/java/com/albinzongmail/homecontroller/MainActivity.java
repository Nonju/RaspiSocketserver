package com.albinzongmail.homecontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "----MAIN----";

    private Button searchBtn, confBtn;
    private TextView twCurrentIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Creating app");
        twCurrentIP = (TextView) findViewById(R.id.main_twCurrentIp);

        searchBtn = (Button) findViewById(R.id.main_searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HUEHEUE", "Searching!");
                Toast.makeText(MainActivity.this, "searching", Toast.LENGTH_SHORT).show();
                twCurrentIP.setText(TAG);
            }
        });

        Log.d(TAG, "Initiating buttons");
        confBtn = (Button) findViewById(R.id.main_configureBtn);
        confBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "In onClickListener");
                confButtonClicked();
            }
        });
    }

    private void confButtonClicked() {
        Log.d(TAG, "ConfButton is clicked");
        Intent intent = new Intent(this, com.albinzongmail.homecontroller.Settings.class);
        Log.d(TAG, "Starting intent");
        startActivity(intent);
    }
}
