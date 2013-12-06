package com.palepail.buttonclicker;

/**
 * Created by palepail on 12/5/13.
 */
public class Button {
    int value = 1;

    public void setValue(int value) {
        this.value += value;
    }

    public int getValue() {
        return value;
    }
}
