package com.dbogucki.lighthouse.models;

import com.dbogucki.lighthouse.enums.Action;

import java.time.LocalTime;

//TODO improve Time checking
//TODO Day in Week feature
public class Schedule {
    private static int seq = 0;

    private int scheduleId;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private Action action;

    public Schedule(String name, LocalTime startTime, LocalTime endTime, Action action) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.action = action;
        this.scheduleId = seq++;
    }

    public boolean between() {
        LocalTime now = LocalTime.now();

        return startTime.isBefore(now) && endTime.isAfter(now);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getScheduleId(){
        return scheduleId;
    }
}
