package fa.group1.services.impl;

import fa.group1.entities.Schedule;
import fa.group1.exceptions.ResourceNotFoundException;
import fa.group1.repository.ScheduleRepository;
import fa.group1.services.ScheduleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    
    @Override
    public List<Schedule> getAllSchedule() {
        List<Schedule> list=scheduleRepository.findAll();
        if(list.isEmpty()){
        	LOGGER.error("Not found any schedule");
            throw new ResourceNotFoundException("Not found any schedule");
        }
        return list;
    }
}
