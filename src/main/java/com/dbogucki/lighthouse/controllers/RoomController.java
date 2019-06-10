package com.dbogucki.lighthouse.controllers;

import com.dbogucki.lighthouse.models.Room;
import com.dbogucki.lighthouse.repositories.RoomsCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/add")
    public String roomAdd(Model model) {
        model.addAttribute("room", new Room("Default"));
        return "addroom";
    }


    @RequestMapping(value = "/add/new", method = RequestMethod.POST)
    public String roomAddNew(@ModelAttribute("room") Room room, BindingResult result, Model model) {
        RoomsCollection.addRoom(room);
        model.addAttribute("room", room);
        return "rooms";
    }
}

