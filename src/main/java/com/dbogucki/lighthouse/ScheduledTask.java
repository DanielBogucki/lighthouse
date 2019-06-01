package com.dbogucki.lighthouse;

import com.dbogucki.lighthouse.repositories.RoomsCollection;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.script.ScriptException;
import java.io.IOException;
import java.time.LocalTime;

@EnableScheduling
@Component("scheduledBean")
public class ScheduledTask {

    @Scheduled(fixedRate = 5000)
    public void work() throws IOException, InterruptedException {
        System.out.println("Scheduled " + LocalTime.now());
        RoomsCollection.updateRooms();
    }

}
