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

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Bulb> list = new ArrayList<>();

        Discoverer discoverer = new YeelightDiscoverer();

        try {
            for(Bulb bulb : discoverer.search()){
                list.add(bulb);
            }

        } catch (DiscoveringException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DeviceSocketException e) {
            e.printStackTrace();
        }
        model.addAttribute("Bulbs", list);
        return "listbulb";
    }

}
