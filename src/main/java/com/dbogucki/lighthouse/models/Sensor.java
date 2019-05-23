package com.dbogucki.lighthouse.models;

import com.dbogucki.lighthouse.enums.SensorType;

public class Sensor {

    private String name;
    private SensorType type;
    private String[] connParam;

    public Sensor(String name, SensorType type, String... params) {
        this.name = name;
        this.type = type;
        connParam = params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public String[] getConnParam() {
        return connParam;
    }

    public void setConnParam(String[] connParam) {
        this.connParam = connParam;
    }
}
