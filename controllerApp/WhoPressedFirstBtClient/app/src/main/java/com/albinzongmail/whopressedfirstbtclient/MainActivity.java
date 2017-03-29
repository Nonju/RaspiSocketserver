package com.albinzongmail.whopressedfirstbtclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private UiUpdater uiUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup "Red-team"-button
        Button selectRedTeamBtn = (Button) findViewById(R.id.main_selectRedTeam);
        selectRedTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { redTeamSelected(); }
        });

        // Setup "Blue-team"-button
        Button selectBlueTeamBtn = (Button) findViewById(R.id.main_selectBlueTeam);
        selectBlueTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { blueTeamSelected(); }
        });

        // Setup Submit-button
        Button submitBtn = (Button) findViewById(R.id.main_submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { sendSubmission(); }
        });

        // Setup UiUpdater
        uiUpdater = new UiUpdater(this, this);

    }

    private void redTeamSelected() {
        CurrentTeam.setCurrentTeam(CurrentTeamEnum.RED);
        uiUpdater.updateTeam();
    }

    private void blueTeamSelected() {
        CurrentTeam.setCurrentTeam(CurrentTeamEnum.BLUE);
        uiUpdater.updateTeam();
    }

    private void sendSubmission() {
        // TODO: Add code to transmit bluetooth message containing name of current team
        String currentTeam = CurrentTeam.getCurrentTeamAsString();
        return; // REMOVE!!
    }
}
