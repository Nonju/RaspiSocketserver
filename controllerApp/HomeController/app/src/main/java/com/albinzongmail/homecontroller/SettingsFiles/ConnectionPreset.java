package com.albinzongmail.homecontroller.SettingsFiles;

/**
 * Created by hannes on 2017-03-29.
 *
 * Object containing preset data about a Connection
 */

public class ConnectionPreset {

    private String name, IP;
    private int port;
    private boolean isSelected;

    public ConnectionPreset(String name, String IP, int port, boolean isSelected) {
        this.name = name;
        this.IP = IP;
        this.port = port;
        this.isSelected = isSelected;
    }
    public ConnectionPreset(String name, String IP, int port) { this(name, IP, port, false); }
    public ConnectionPreset(String name, String IP) { this(name, IP, 1337); }
    public ConnectionPreset(String IP) { this("New preset", IP); }
    public ConnectionPreset() { this("127.0.0.1"); } // set localhost as default IP value

    // Get
    public String getName() { return name; }
    public String getIP() { return this.IP; }
    public int getPort() { return port; }
    public boolean isSelected() { return isSelected; }

    // Set
    public void setName(String name) { this.name = name; }
    public void setIP(String value) { this.IP = value; }
    public void setPort(int port) { this.port = port; }
    public void setSelected(boolean selected) { isSelected = selected; }


}
