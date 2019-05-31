package com.dbogucki.lighthouse.models;

import com.dbogucki.bulbapi.devices.Bulb;
import com.dbogucki.bulbapi.exceptions.DeviceSocketException;
import com.dbogucki.bulbapi.exceptions.ResultException;
import com.dbogucki.lighthouse.enums.Action;
import com.dbogucki.lighthouse.enums.LightValue;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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

    private int lightValue = 0;
    private int changeLightValue = 0;

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
                        b.setColorTemperature(LightValue.REST_TEMP.getValue());
                        b.setBrightness(70);
                        break;

                    case WORK_LIGHT:
                        b.setPower(true);
                        b.setColorTemperature(LightValue.WORK_TEMP.getValue());
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

    public void readSensor() throws IOException, InterruptedException {
        if (this.lightSensor != null) {
            Resource resource = new ClassPathResource(lightSensor.getType().getPath());
            File file = resource.getFile();
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getPath());

            Process p = Runtime.getRuntime().exec("sudo python " + file.getAbsolutePath()); //"/bin/bash", "-c",
            p.waitFor();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lightValue = Integer.parseInt(line);
                }
            }
        }
    }

    public void updateRoom() throws IOException, InterruptedException {
        this.readSensor();
        Schedule schedule = this.checkForSchedule();
        if (schedule != null) {
            this.setLights(schedule.getAction());
        } else if (this.getLightSensor() != null && this.getLightValue() < LightValue.MIN.getValue()) {
            this.setLights(Action.POWER_ON);
        }
        else if(this.getLightSensor() != null && this.getLightValue() > LightValue.MIN.getValue() + this.getChangeLightValue()){
            this.setLights(Action.POWER_OFF);
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

    public int getLightValue() {
        return lightValue;
    }

    public void setLightValue(int lightValue) {
        this.lightValue = lightValue;
    }

    public int getChangeLightValue() {
        return changeLightValue;
    }

    public void setChangeLightValue(int changeLightValue) {
        this.changeLightValue = changeLightValue;
    }
}
