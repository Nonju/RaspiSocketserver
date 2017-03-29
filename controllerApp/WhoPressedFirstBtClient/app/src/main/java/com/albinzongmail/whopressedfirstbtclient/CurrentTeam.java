package com.albinzongmail.whopressedfirstbtclient;

/**
 * Created by hannes on 2017-03-25.
 *
 * Static class to keep track of the users
 * currently selected currTeam.
 */

public class CurrentTeam {

    // Current selection
    private static CurrentTeamEnum currTeam = CurrentTeamEnum.RED;

    // Set
    public static void setCurrentTeam(CurrentTeamEnum newTeam) { currTeam = newTeam; }

    // Get
    public static CurrentTeamEnum getCurrentTeam() { return currTeam; }
    public static String getCurrentTeamAsString() {
        if (currTeam.equals(CurrentTeamEnum.BLUE)) return "Blue";
        else return "Red";
    }
}
