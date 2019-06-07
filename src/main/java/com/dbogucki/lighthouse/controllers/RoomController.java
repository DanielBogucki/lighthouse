package com.dbogucki.lighthouse.controllers;

import com.dbogucki.lighthouse.models.Room;
import com.dbogucki.lighthouse.repositories.RoomsCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("Rooms", RoomsCollection.getRooms());
        return "rooms";
    }

    @RequestMapping("/{roomId}")
    public String roomDetails(@PathVariable("roomId") int id, Model model) {

        Room chosen = RoomsCollection.getRoomById(id);

        model.addAttribute("room", chosen);
        model.addAttribute("bulbs", chosen.getBulbs());
        model.addAttribute("sensor", chosen.getLightSensor());
        model.addAttribute("schedules", chosen.getSchedules());
        return "room";
    }

    @RequestMapping("/add")
    public String roomAdd(Model model) {
        return "addroom";
    }

    @RequestMapping("/add/{name}")
    public String roomAddNew(@PathVariable("name") String name, Model model) {
        Room newRoom = new Room("name");
        RoomsCollection.addRoom(newRoom);
        int i = RoomsCollection.getRooms().size()-1;
        return "room/"+1;
    }
}

