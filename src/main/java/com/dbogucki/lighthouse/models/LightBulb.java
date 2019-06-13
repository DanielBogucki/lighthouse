package com.dbogucki.lighthouse.models;

import com.dbogucki.bulbapi.devices.Bulb;

public class LightBulb {
    private static int seq = 0;
    private int bulbId;
    private Bulb bulb;

    public LightBulb(Bulb bulb){
        this.bulb = bulb;
        bulbId=seq++;
    }

    public Bulb getBulb() {
        return bulb;
    }

    public int getBulbId() {
        return bulbId;
    }
}
