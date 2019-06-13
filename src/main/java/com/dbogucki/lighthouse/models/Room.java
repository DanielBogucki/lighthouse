package com.dbogucki.lighthouse.models;

import com.dbogucki.bulbapi.devices.Bulb;
import com.dbogucki.bulbapi.exceptions.DeviceSocketException;
import com.dbogucki.bulbapi.exceptions.ResultException;
import com.dbogucki.lighthouse.enums.Action;
import com.dbogucki.lighthouse.enums.LightValue;
import com.dbogucki.lighthouse.repositories.RoomsCollection;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room {
    private static int seq = 0;

    private int roomId;
    private String name;

    private Set<LightBulb> bulbs = new HashSet<>();
    private List<Schedule> schedules = new ArrayList<>();
    private Sensor lightSensor;

    private double lightValue = 0;
    private double changeLightValue = 100;

    //TODO store and check current/last Schedule

    public Room() {
        this("Default Room");
    }

    public Room(String name) {
        this.name = name;
        roomId = seq++;
    }

    public Schedule checkForSchedule() {
        Schedule current = null;
        LocalTime now = LocalTime.now();
        for (Schedule s : schedules) {
            if (s.between()) {
                current = s;
                break;
            }
        }
        return current;
    }

    public void setLights(Action action) {
        for (LightBulb b : bulbs) {
            try {
                switch (action) {
                    case POWER_ON:
                        b.getBulb().setPower(true);
                        b.getBulb().setColorTemperature(LightValue.POWER_ON.getValue());
                        b.getBulb().setBrightness(100);
                        break;

                    case POWER_OFF:
                        b.getBulb().setPower(false);
                        break;

                    case LAZY_LIGHT:
                        b.getBulb().setPower(true);
                        b.getBulb().setColorTemperature(LightValue.REST_TEMP.getValue());
                        b.getBulb().setBrightness(70);
                        break;

                    case WORK_LIGHT:
                        b.getBulb().setPower(true);
                        b.getBulb().setColorTemperature(LightValue.WORK_TEMP.getValue());
                        b.getBulb().setBrightness(100);
                        break;

                }

            } catch (ResultException e) {
                e.printStackTrace();
            } catch (DeviceSocketException e) {
                e.printStackTrace();
            }
        }
    }

    public void readSensor() throws IOException, InterruptedException {
        if (this.lightSensor != null) {
            lightValue = lightSensor.getValue();
        }
    }

    public void updateRoom() throws IOException, InterruptedException {
        Schedule schedule = this.checkForSchedule();
        if (schedule != null) {
            this.setLights(schedule.getAction());
        } else {
            this.readSensor();
            if (this.getLightSensor() != null && this.getLightValue() < LightValue.MIN.getValue()) {
                this.setLights(Action.POWER_ON);
            }
            if (this.getLightSensor() != null && this.getLightValue() > LightValue.MIN.getValue() + this.getChangeLightValue()) {
                this.setLights(Action.POWER_OFF);
            }
        }
    }

    public boolean addBulb(Bulb bulb) {
        return bulbs.add(new LightBulb(bulb));
    }

    public boolean removeBulb(Bulb bulb) {
        return bulbs.remove(bulb);
    }

    public Set<LightBulb> getBulbs() {
        return bulbs;
    }

    public boolean addSchedule(Schedule schedule) {
        return schedules.add(schedule);
    }

    public boolean removeSchedule(Schedule schedule) {
        return schedules.remove(schedule);
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sensor getLightSensor() {
        return lightSensor;
    }

    public void setLightSensor(Sensor lightSensor) {
        this.lightSensor = lightSensor;
    }

    public double getLightValue() {
        return lightValue;
    }

    public void setLightValue(double lightValue) {
        this.lightValue = lightValue;
    }

    public double getChangeLightValue() {
        return changeLightValue;
    }

    public void setChangeLightValue(double changeLightValue) {
        this.changeLightValue = changeLightValue;
    }

    public LightBulb getLighBulb(int id) {
        LightBulb bulb = null;
        for (LightBulb lb : bulbs) {
            if (lb.getBulbId() == id) bulb = lb;
        }
        return bulb;
    }

    public Schedule getSchedule(int id){
        Schedule schedule = null;
        for(Schedule s : schedules){
            if(s.getScheduleId()==id) schedule=s;
        }
        return schedule;
    }
}
