package com.palepail.buttonclicker.Upgrades;

import com.palepail.buttonclicker.Clicks;
import com.palepail.buttonclicker.ClicksPerSecond;

/**
 * Created by palepail on 12/5/13.
 */
public class Upgrade {
    String name;
    int cost;
    int value;
    float costGrowth;
    int level = 0;
    protected static Upgrade upgrade;

    public void buyUpgrade() {
        Clicks.getClicksObject().addClicks(-cost);
        ClicksPerSecond.getCPSObject().addCPS(value);
        this.cost = Math.round(this.cost * this.costGrowth);
        this.level++;
    }

    public int getValue() {
        return this.value;
    }

    public float getCostGrowth() {
        return this.costGrowth;
    }
    public int getCost() {
        return this.cost;
    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }
}
