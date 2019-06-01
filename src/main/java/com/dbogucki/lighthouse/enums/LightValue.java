package com.dbogucki.lighthouse.enums;

public enum LightValue {
    MIN(500),
    REST_TEMP(2700),
    WORK_TEMP(5000);

    private int value;

    LightValue(int value){
        this.value = value;
    }

    public int getValue(){return value;}

}