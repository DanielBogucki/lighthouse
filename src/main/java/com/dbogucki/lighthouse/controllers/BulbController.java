package com.dbogucki.lighthouse.controllers;

import com.dbogucki.bulbapi.devices.Bulb;
import com.dbogucki.bulbapi.devices.YeelightBulb;
import com.dbogucki.bulbapi.discovering.Discoverer;
import com.dbogucki.bulbapi.discovering.DiscoveryManager;
import com.dbogucki.bulbapi.discovering.YeelightDiscoverer;
import com.dbogucki.bulbapi.exceptions.DeviceSocketException;
import com.dbogucki.bulbapi.exceptions.DiscoveringException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
