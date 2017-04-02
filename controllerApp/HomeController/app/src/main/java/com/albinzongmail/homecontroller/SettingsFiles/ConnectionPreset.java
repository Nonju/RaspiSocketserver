package com.albinzongmail.homecontroller.SettingsFiles;

/**
 * Created by hannes on 2017-03-29.
 *
 * Object containing preset data about a Connection
 */

public class ConnectionPreset {

    private String IP;
    private boolean isSelected;

    public ConnectionPreset(String IP, boolean isSelected) {
        this.IP = IP;
        this.isSelected = isSelected;
    }
    public ConnectionPreset(String IP) { this(IP, false); }
    public ConnectionPreset() { this("127.0.0.1"); } // set localhost as default IP value

    // Get
    public String getIP() { return this.IP; }
    public void setSelected(boolean selected) { isSelected = selected; }

    // Set
    public void setIP(String value) { this.IP = value; }
    public boolean isSelected() { return isSelected; }


}
