package com.dbogucki.lighthouse.controllers;

import com.dbogucki.lighthouse.enums.Action;
import com.dbogucki.lighthouse.enums.SensorType;
import com.dbogucki.lighthouse.models.Room;
import com.dbogucki.lighthouse.models.Schedule;
import com.dbogucki.lighthouse.models.Sensor;
import com.dbogucki.lighthouse.repositories.RoomsCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
    @RequestMapping(value = "/{roomId}/{scheduleId}", method= RequestMethod.GET)
    public String scheduleInfo(@PathVariable("roomId") int roomId, @PathVariable("scheduleId") int scheduleId, Model model) {
        Room room = RoomsCollection.getRoomById(roomId);
        Schedule schedule = room.getSchedule(scheduleId);
        model.addAttribute("schedule", schedule);
        model.addAttribute("room", RoomsCollection.getRoomById(roomId));
        model.addAttribute("actions", Action.values());
        return "schedule";
    }

    @RequestMapping(value = "/add/{roomId}", method= RequestMethod.GET)
    public String scheduleAdd(@PathVariable("roomId") int roomId, Model model) {
        model.addAttribute("room", RoomsCollection.getRoomById(roomId));
        model.addAttribute("actions", Action.values());
        return "addSchedule";
    }

    @RequestMapping(value = "/add/new", method = RequestMethod.POST)
    public String scheduleAddNew(HttpServletRequest request){
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String name = request.getParameter("name");
        Action action = Action.getEnum(request.getParameter("action"));
        String startTimeString = request.getParameter("start");
        String endTimeString = request.getParameter("end");

        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalTime endTime = LocalTime.parse(endTimeString);

        Schedule schedule = new Schedule(name, startTime, endTime, action);
        RoomsCollection.getRoomById(roomId).addSchedule(schedule);
        return "addSchedule";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteSchedule(HttpServletRequest request){
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));

        Room room = RoomsCollection.getRoomById(roomId);
        Schedule schedule = room.getSchedule(scheduleId);
        room.getSchedules().remove(schedule);

        return "rooms";
    }
}