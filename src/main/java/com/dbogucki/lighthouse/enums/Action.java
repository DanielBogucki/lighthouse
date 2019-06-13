package com.dbogucki.lighthouse.enums;

public enum Action {
    POWER_ON("Power On"),
    POWER_OFF("Power Off"),
    WORK_LIGHT("Work light"),
    LAZY_LIGHT("Lazy light");

    private String value;

    Action(String type) {
        this.value = type;
    }

    public String getValue(){return value;}

    public static Action getEnum(String s){
        return Action.valueOf(s);
    }
}
