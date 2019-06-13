package com.dbogucki.lighthouse.enums;

public enum SensorType {
    BH1750("python/bh1750.py");

    private String path;

    SensorType(String path) {
        this.path = path;
    }

    public String getPath(){return path;}

    public String getName(){return this.name();}
}
