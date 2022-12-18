package fa.group1.services;

import fa.group1.entities.CinemaRoom;
import fa.group1.entities.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CinemaRoomService {
    Page<CinemaRoom> getAllCinemaRoom(int page,int size);
    List<Seat> getAllSeatByCinemaID(Integer cinemaID);

    List<Seat> updateSeatInCinemaRoom(List<Seat> list);
}
