package com.albinzongmail.homecontroller.Util.Exceptions;

/**
 * Created by hannes on 2017-04-02.
 */

public class InvalidIpAddress extends Exception {
    public InvalidIpAddress() { this("IP address was invalid"); }
    public InvalidIpAddress(String message) { super(message); }
}
