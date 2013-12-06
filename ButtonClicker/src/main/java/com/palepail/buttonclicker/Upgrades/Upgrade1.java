package com.palepail.buttonclicker.Upgrades;

/**
 * Created by palepail on 12/5/13.
 */
public class Upgrade1 extends Upgrade {

    public Upgrade1() {
        name = "Upgrade 1";
        cost = 10;
        costGrowth = 1.3f;
        value = 1;
        upgrade=this;
    }

    public static Upgrade getUpgradeObject(){
        if (upgrade==null){
         return new Upgrade1();
        }
        return upgrade;
    }


}
