package fa.group1.services.impl;

import fa.group1.entities.CinemaRoom;
import fa.group1.entities.Seat;
import fa.group1.exceptions.ResourceNotFoundException;
import fa.group1.repository.CinemaRoomRepository;
import fa.group1.repository.SeatRepository;
import fa.group1.services.CinemaRoomService;
import lombok.var;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CinemaRoomServiceImpl implements CinemaRoomService {
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    CinemaRoomRepository cinemaRoomRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CinemaRoomServiceImpl.class);

    @Override
    public Page<CinemaRoom> getAllCinemaRoom(int page, int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<CinemaRoom> list = cinemaRoomRepository.findAll(paging);
        return list;

    }

    @Override
    public List<Seat> getAllSeatByCinemaID(Integer cinemaID) {
        List<Seat> seats = seatRepository.findByCinemaRoomID(cinemaID);
        if (seats.isEmpty()) {
        	LOGGER.error("No seat in this cinema room");
            throw new ResourceNotFoundException("No seat in this cinema room");
        }
        return seats;
    }

    @Override
    public List<Seat> updateSeatInCinemaRoom(List<Seat> list) {
        return seatRepository.saveAll(list);
    }
}
