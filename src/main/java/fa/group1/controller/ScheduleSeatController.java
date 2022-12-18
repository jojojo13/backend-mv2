package fa.group1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.group1.entities.ScheduleSeat;
import fa.group1.services.ScheduleSeatService;

@RestController
@RequestMapping(path="api/scheduleSeat")
@CrossOrigin
public class ScheduleSeatController {

    @Autowired
    private ScheduleSeatService scheduleSeatService;
    @GetMapping("")
    public ResponseEntity<?> getSchedule() {
        List<ScheduleSeat> list = scheduleSeatService.findAllScheduleSeat();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
