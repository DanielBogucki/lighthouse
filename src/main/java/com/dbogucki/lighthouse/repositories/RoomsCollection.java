package com.dbogucki.lighthouse.repositories;

import com.dbogucki.lighthouse.models.Room;
import com.dbogucki.lighthouse.models.Schedule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomsCollection {
    private static List<Room> list = new ArrayList<>();

    public static void addRoom(Room room) {
        list.add(room);
    }

    public static List<Room> getRooms() {
        return list;
    }

    public static Room getRoomById(Integer id) {
        if (id < list.size()) {
            return list.get(id);
        } else {
            return null;
        }
    }

    public static void updateRooms() {
        for (Room room : list) {
            Schedule schedule = room.checkForSchedule();
            if (schedule != null) {
                room.setLights(schedule.getAction());
            }
        }
    }
}
