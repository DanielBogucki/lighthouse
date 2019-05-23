package com.dbogucki.lighthouse;

import com.dbogucki.bulbapi.devices.Bulb;
import com.dbogucki.bulbapi.devices.YeelightBulb;
import com.dbogucki.bulbapi.exceptions.DeviceSocketException;
import com.dbogucki.lighthouse.enums.Action;
import com.dbogucki.lighthouse.models.Room;
import com.dbogucki.lighthouse.models.Schedule;
import com.dbogucki.lighthouse.repositories.RoomsCollection;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Component
public class StartupLighthouse implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Room pokoj = new Room("Mój pokój");

        Schedule noc1 = new Schedule("Noc1", LocalTime.parse("22:00"), LocalTime.parse("23:59"), Action.POWER_OFF);
        Schedule noc2 = new Schedule("Noc2", LocalTime.parse("00:00"), LocalTime.parse("06:30"), Action.POWER_OFF);
        Schedule poranek = new Schedule("Poranek", LocalTime.parse("06:30"), LocalTime.parse("07:30"), Action.POWER_OFF);
        Schedule praca = new Schedule("Praca", LocalTime.parse("16:30"),LocalTime.parse("18:00"),Action.WORK_LIGHT);

        System.out.println(noc1.between());
        System.out.println(noc2.between());
        pokoj.addSchedule(noc1);
        pokoj.addSchedule(noc2);
        pokoj.addSchedule(poranek);
        pokoj.addSchedule(praca);

        try {
            YeelightBulb bulb = new YeelightBulb("192.168.8.111");
            pokoj.addBulb(bulb);
        } catch (DeviceSocketException e) {
            e.printStackTrace();
        }


        RoomsCollection.addRoom(pokoj);
        RoomsCollection.updateRooms();

        List<Room> pokoje = RoomsCollection.getRooms();

        System.out.println("Szukam pokoi");
        for (Room r : pokoje){
            Set<Bulb> bulbs = r.getBulbs();

            for (Bulb b : bulbs){
                System.out.println(b.getIp());
            }
        }

    }
}
