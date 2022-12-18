package fa.group1.services;

import fa.group1.dto.MovieByTypeDTO;
import fa.group1.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import fa.group1.dto.MovieAddDTO;

import java.util.List;
import java.util.Map;

public interface MovieService {
	Page<Movie> getAllMovieByPaging(int page, int size);
	Movie checkMovie(MovieAddDTO movie, String cinemaRoomId);

	Movie findMovieByID(Integer id);
	Page<Movie> getAllMovieBySearch(String searchTxt,int page,int size);
	List<MovieByTypeDTO> getMovieByType(Long typeID);
}
