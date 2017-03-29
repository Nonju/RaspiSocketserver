package com.albinzongmail.whopressedfirstbtclient;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

/**
 * Created by hannes on 2017-03-25.
 *
 * Updates UI changes such as changing team or updating scores.
 */

public class UiUpdater {

    Activity activity;
    Context context;

    public UiUpdater(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
        updateTeam();
    }

    public void updateTeam() {
        // Get current team selection
        CurrentTeamEnum currTeam = CurrentTeam.getCurrentTeam();
        // Get buttons
        Button redBtn = (Button) this.activity.findViewById(R.id.main_selectRedTeam);
        Button blueBtn = (Button) this.activity.findViewById(R.id.main_selectBlueTeam);

        if (currTeam.equals(CurrentTeamEnum.RED)) {
            // Set red team color as selected
            redBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.redTeamSelected));
            // Unselect other team (change color to its unselected one)
            blueBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.blueTeamUnselected));

        }
        else if (currTeam.equals(CurrentTeamEnum.BLUE)) {
            // Set blue team color as selected
            blueBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.redTeamSelected));
            // Unselect other team (change color to its unselected one)
            redBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.blueTeamUnselected));
        }
    }

}
