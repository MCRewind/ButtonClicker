package com.palepail.buttonclicker.Upgrades;

/**
 * Created by palepail on 12/10/13.
 */
public class Upgrade3 extends Upgrade{
    protected static Upgrade upgrade;
    public Upgrade3() {
        this.name = "Upgrade 3";
        this.cost = 1000;
        this.costGrowth = 1.3f;
        this.value = 50;
        this.level = 0;
        upgrade = this;
    }

    public static Upgrade getUpgradeObject() {
        if (upgrade == null) {
            return new Upgrade3();
        }
        return upgrade;
    }
}
