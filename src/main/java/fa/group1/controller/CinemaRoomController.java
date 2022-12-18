package fa.group1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fa.group1.entities.Movie;
import fa.group1.entities.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fa.group1.entities.CinemaRoom;
import fa.group1.services.CinemaRoomService;

@RestController
@RequestMapping(path = "api/cinemaroom")
@CrossOrigin
public class CinemaRoomController {
    @Autowired
    CinemaRoomService cinemaRoomService;

    @GetMapping("")
    public ResponseEntity<?> getCinemaRoom(@RequestParam int index,
                                           @RequestParam int size) {
        Pageable paging = PageRequest.of(index, size);
        Page<CinemaRoom> pageMovies=cinemaRoomService.getAllCinemaRoom(index,size);
        Map<String, Object> response = new HashMap<>();
        List<Movie> list = new ArrayList<>();
        response.put("cinemas", pageMovies.getContent());
        response.put("currentPage", pageMovies.getNumber());
        response.put("totalItem", pageMovies.getTotalElements());
        response.put("totalPage", pageMovies.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("seat")
    public ResponseEntity<?> getSeatByCinemaRoom(@RequestParam Integer cinemaID) {

            List<Seat> list = cinemaRoomService.getAllSeatByCinemaID(cinemaID);
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("updateSeat")
    public ResponseEntity<?> updateSeatInCinemaRoom(@RequestBody List<Seat> list) {
        Map<String, Object> response = new HashMap<>();
        List<Seat> listSeat = cinemaRoomService.updateSeatInCinemaRoom(list);
        response.put("message", "Updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
