package fa.group1.controller;

import fa.group1.entities.Schedule;

import fa.group1.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/schedule")
@CrossOrigin
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @GetMapping("")
    public ResponseEntity<?> getSchedule() {
        List<Schedule> list = scheduleService.getAllSchedule();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
