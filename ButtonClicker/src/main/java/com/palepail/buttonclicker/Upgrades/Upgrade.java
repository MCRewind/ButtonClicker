package com.palepail.buttonclicker.Upgrades;

import com.palepail.buttonclicker.Clicks;
import com.palepail.buttonclicker.ClicksPerSecond;

import java.util.ArrayList;

/**
 * Created by palepail on 12/5/13.
 */
public class Upgrade {
    String name;
    int cost;
    int value;
    float costGrowth;
    int level = 0;
    private static ArrayList<Upgrade> upgrades =  new ArrayList<Upgrade>();

    public void buyUpgrade() {
        Clicks.getClicksObject().subClicks(cost);
        ClicksPerSecond.getCPSObject().addCPS(value);
        this.cost = Math.round(this.cost * this.costGrowth);
        this.level++;
    }

    public static ArrayList<Upgrade> getUpgrades(){
        if (upgrades.size()==0)
        {
            upgrades.add(Upgrade1.getUpgradeObject());
            upgrades.add(Upgrade2.getUpgradeObject());
            upgrades.add(Upgrade3.getUpgradeObject());
        }
        return upgrades;
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
