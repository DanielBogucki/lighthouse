package com.dbogucki.lighthouse.controllers;

import com.dbogucki.bulbapi.devices.Bulb;
import com.dbogucki.bulbapi.discovering.Discoverer;
import com.dbogucki.bulbapi.discovering.YeelightDiscoverer;
import com.dbogucki.bulbapi.exceptions.DeviceSocketException;
import com.dbogucki.bulbapi.exceptions.DiscoveringException;
import com.dbogucki.lighthouse.repositories.RoomsCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("Rooms", RoomsCollection.getRooms());
        return "rooms";
    }

    @RequestMapping("/{roomId}")
    public String roomDetails(@PathVariable("roomId") int id, Model model){

        model.addAttribute("Room", RoomsCollection.getRoomById(id));
        return "room";
        }
}
