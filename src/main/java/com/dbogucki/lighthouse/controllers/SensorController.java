package com.dbogucki.lighthouse.controllers;

import com.dbogucki.lighthouse.enums.SensorType;
import com.dbogucki.lighthouse.models.Sensor;
import com.dbogucki.lighthouse.repositories.RoomsCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sensors")
public class SensorController {
    @RequestMapping(value = "/add/{roomId}", method= RequestMethod.GET)
    public String sensorAdd(@PathVariable("roomId") int roomId, Model model) {
        model.addAttribute("room", RoomsCollection.getRoomById(roomId));
        model.addAttribute("types", SensorType.values());
        return "addSensor";
    }

    @RequestMapping(value = "/add/new", method = RequestMethod.POST)
    public String sensorAddNew(HttpServletRequest request){
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String name = request.getParameter("name");
        SensorType type = SensorType.valueOf(request.getParameter("type"));
        String addr = request.getParameter("address");
        Sensor sensor = new Sensor(name, type, addr);
        RoomsCollection.getRoomById(roomId).setLightSensor(sensor);
        return "addSensor";
    }
}
