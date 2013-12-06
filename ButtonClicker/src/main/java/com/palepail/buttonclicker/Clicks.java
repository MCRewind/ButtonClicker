package com.palepail.buttonclicker;

/**
 * Created by palepail on 12/5/13.
 */
public class Clicks {
    private long clicks = 0;
    private static Clicks click;

    public static Clicks getClicksObject() {
        if (click == null) {
            click = new Clicks();
        }
        return click;
    }

    public void addClicks(int number) {
        clicks += number;
    }

    public long getClicks() {
        return clicks;
    }
}
