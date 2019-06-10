package com.dbogucki.lighthouse;

import com.dbogucki.bulbapi.devices.Bulb;
import com.dbogucki.bulbapi.devices.YeelightBulb;
import com.dbogucki.bulbapi.exceptions.DeviceSocketException;
import com.dbogucki.lighthouse.enums.Action;
import com.dbogucki.lighthouse.enums.SensorType;
import com.dbogucki.lighthouse.models.Room;
import com.dbogucki.lighthouse.models.Schedule;
import com.dbogucki.lighthouse.models.Sensor;
import com.dbogucki.lighthouse.repositories.RoomsCollection;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class StartupLighthouse implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Room pokoj = new Room("Mój pokój");
        Room salon = new Room("Sypialnia");
        Room kuchnia = new Room("Kuchnia");

        Schedule noc1 = new Schedule("Noc1", LocalTime.parse("22:00"), LocalTime.parse("23:59"), Action.POWER_OFF);
        Schedule noc2 = new Schedule("Noc2", LocalTime.parse("00:00"), LocalTime.parse("06:30"), Action.POWER_OFF);
        Schedule poranek = new Schedule("Poranek", LocalTime.parse("06:30"), LocalTime.parse("07:30"), Action.POWER_OFF);
        Schedule praca = new Schedule("Praca", LocalTime.parse("16:30"), LocalTime.parse("18:00"), Action.WORK_LIGHT);
        Schedule odpoczynek = new Schedule("Odpoczynek", LocalTime.parse("18:00"), LocalTime.parse("20:00"), Action.LAZY_LIGHT);
        Schedule nowy = new Schedule("nowy", LocalTime.parse("08:00"), LocalTime.parse("12:00"), Action.WORK_LIGHT);

        pokoj.addSchedule(noc1);
        pokoj.addSchedule(noc2);
        //pokoj.addSchedule(nowy);
        pokoj.addSchedule(poranek);
        pokoj.addSchedule(praca);
        pokoj.addSchedule(odpoczynek);

        // MQTT i AMQP

        try {
            YeelightBulb bulb = new YeelightBulb("192.168.43.113");
            pokoj.addBulb(bulb);
        } catch (DeviceSocketException e) {
            e.printStackTrace();
        }


        pokoj.setLightSensor(new Sensor("BH1750", SensorType.BH1750));


        RoomsCollection.addRoom(pokoj);
        RoomsCollection.addRoom(salon);
        RoomsCollection.addRoom(kuchnia);



    }
}
