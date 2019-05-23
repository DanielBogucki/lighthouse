package com.dbogucki.lighthouse.enums;

public enum SensorType {
    BH1750("BH1750");

    private String value;

    SensorType(String type) {
        this.value = type;
    }

    public String getValue(){return value;}
}
