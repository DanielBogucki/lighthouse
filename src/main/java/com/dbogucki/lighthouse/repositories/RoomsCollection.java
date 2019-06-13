package com.dbogucki.lighthouse.repositories;

import com.dbogucki.lighthouse.models.Room;
import org.springframework.stereotype.Repository;

import java.io.IOException;
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
        for (Room room : list) {
            if (room.getRoomId() == id) return room;
        }
        return null;
    }

    public static void updateRooms() throws IOException, InterruptedException {
        for (Room room : list) {
            room.updateRoom();
        }
    }
}
