package com.palepail.buttonclicker;

/**
 * Created by palepail on 12/5/13.
 */
public class ClicksPerSecond {
    int CPS = 0;
    private static ClicksPerSecond CPSObject;

    public static ClicksPerSecond getCPSObject() {
        if (CPSObject == null) {
            CPSObject = new ClicksPerSecond();
        }
        return CPSObject;
    }

    public int getCPS() {
        return CPS;
    }

    public void addCPS(int num) {
        CPS += num;
    }
}
