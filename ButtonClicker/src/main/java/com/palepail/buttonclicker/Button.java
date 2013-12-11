package com.palepail.buttonclicker;

import org.joda.time.DateTime;


/**
 * Created by palepail on 12/5/13.
 */
public class Button {
    private int value = 1;
    private static int manualClicks = 0;
    private static DateTime firstClick;

    public void setValue(int value) {
        this.value += value;
    }

    public int getValue() {
        return value;
    }

    public void addManualClicks() {
        manualClicks++;
        if (firstClick == null) {
            firstClick = new DateTime();
        }
    }

    public static DateTime getFirstClick() {
        return firstClick;
    }


    public static int getManualClicks() {
        return manualClicks;
    }
}
