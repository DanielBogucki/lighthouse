package com.dbogucki.lighthouse.controllers;

import com.dbogucki.bulbapi.devices.Bulb;
import com.dbogucki.bulbapi.devices.YeelightBulb;
import com.dbogucki.bulbapi.discovering.Discoverer;
import com.dbogucki.bulbapi.discovering.DiscoveryManager;
import com.dbogucki.bulbapi.discovering.YeelightDiscoverer;
import com.dbogucki.bulbapi.enums.Category;
import com.dbogucki.bulbapi.exceptions.DeviceNotRecognizedException;
import com.dbogucki.bulbapi.exceptions.DeviceSocketException;
import com.dbogucki.bulbapi.exceptions.DiscoveringException;
import com.dbogucki.bulbapi.factories.BulbFactory;
import com.dbogucki.lighthouse.models.LightBulb;
import com.dbogucki.lighthouse.models.Room;
import com.dbogucki.lighthouse.repositories.RoomsCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bulbs")
public class BulbController {

    @RequestMapping("/search")
    public String list(Model model) {
        List<Bulb> list = new ArrayList<>();

        Discoverer discoverer = new YeelightDiscoverer();

        try {
            list.addAll(discoverer.search());

        } catch (DiscoveringException | IOException | DeviceSocketException e) {
            e.printStackTrace();
        }

        model.addAttribute("bulbs", list);
        return "searchbulbs";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String bulbInfo(@RequestParam("roomId") int roomId, @RequestParam("bulbId") int bulbId, Model model) {
        Room room = RoomsCollection.getRoomById(roomId);
        LightBulb bulb = room.getLighBulb(bulbId);

        model.addAttribute("bulb", bulb);
        model.addAttribute("room", room);
        return "bulbs";
    }

    @RequestMapping(value = "/add")
    public String bulbAdd(Model model) throws DeviceNotRecognizedException, DeviceSocketException {

        try {
            model.addAttribute("bulb", BulbFactory.getBulb(Category.DEFAULT, "", 0));
            model.addAttribute("category", Category.values());
        }catch(Exception e){

        }
        return "addbulb";
    }

    @RequestMapping(value = "/add/new/{roomId}", method = RequestMethod.POST)
    public String bulbAddNew(@RequestParam("roomId") int roomId, @ModelAttribute("bulb") Bulb bulb, BindingResult result, Model model) {

        RoomsCollection.getRoomById(roomId).addBulb(bulb);
        model.addAttribute("bulb", bulb);
        return "room";
    }


}
