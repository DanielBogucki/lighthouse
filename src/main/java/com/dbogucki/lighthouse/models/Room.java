package com.dbogucki.lighthouse.models;

import com.dbogucki.bulbapi.devices.Bulb;
import com.dbogucki.bulbapi.exceptions.DeviceSocketException;
import com.dbogucki.bulbapi.exceptions.ResultException;
import com.dbogucki.lighthouse.enums.Action;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room {
    private String name;

    private Set<Bulb> bulbs = new HashSet<>();
    private List<Schedule> schedules = new ArrayList<>();
    private Sensor lightSensor;

    //TODO store and check current/last Schedule

    public Room() {
        this("Default Room");
    }

    public Room(String name) {
        this.name = name;
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
        for (Bulb b : bulbs) {
            try {
                switch (action) {
                    case POWER_ON:
                        b.setPower(true);
                        break;

                    case POWER_OFF:
                        b.setPower(false);
                        break;

                    case LAZY_LIGHT:
                        b.setPower(true);
                        b.setColorTemperature(2000);
                        b.setBrightness(70);
                        break;

                    case WORK_LIGHT:
                        b.setPower(true);
                        b.setColorTemperature(6500);
                        b.setBrightness(100);
                        break;

                }

            } catch (ResultException e) {
                e.printStackTrace();
            } catch (DeviceSocketException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addBulb(Bulb bulb) {
        return bulbs.add(bulb);
    }

    public boolean removeBulb(Bulb bulb) {
        return bulbs.remove(bulb);
    }

    public Set<Bulb> getBulbs() {
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
}
