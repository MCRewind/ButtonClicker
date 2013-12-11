package com.palepail.buttonclicker.Upgrades;

/**
 * Created by palepail on 12/6/13.
 */
public class Upgrade2 extends Upgrade {
    protected static Upgrade upgrade;

    public Upgrade2() {
        this.name = "Upgrade 2";
        this.cost = 100;
        this.costGrowth = 1.3f;
        this.value = 10;
        this.level = 0;
        upgrade = this;
    }

    public static Upgrade getUpgradeObject() {
        if (upgrade == null) {
            return new Upgrade2();
        }
        return upgrade;
    }

}
