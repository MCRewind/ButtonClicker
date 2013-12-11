package com.palepail.buttonclicker.Upgrades;

/**
 * Created by palepail on 12/5/13.
 */
public class Upgrade1 extends Upgrade {
    protected static Upgrade upgrade;

    public Upgrade1() {
        this.name = "Upgrade 1";
        this.cost = 20;
        this.costGrowth = 1.3f;
        this.value = 1;
        this.level = 0;
        upgrade = this;
    }

    public static Upgrade getUpgradeObject() {
        if (upgrade == null) {
            return new Upgrade1();
        }
        return upgrade;
    }


}
