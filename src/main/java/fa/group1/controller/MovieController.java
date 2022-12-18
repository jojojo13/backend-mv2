package fa.group1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fa.group1.dto.MovieAddDTO;
import fa.group1.dto.MovieByTypeDTO;
import fa.group1.services.MovieService;
import fa.group1.services.impl.MovieServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import fa.group1.entities.Movie;
import fa.group1.repository.MovieRepository;

@RestController
@RequestMapping(value = "api/movie")
@CrossOrigin
public class MovieController {
    @Autowired
    private MovieService movieService;


    @GetMapping("")
    public ResponseEntity<?> getMovie(@RequestParam int index,
                                      @RequestParam int size) {
        Map<String, Object> response = new HashMap<>();

        List<Movie> list = new ArrayList<>();
        Page<Movie> pageMovies = movieService.getAllMovieByPaging(index, size);
        list = pageMovies.getContent();
        response.put("movie", list);
        response.put("currentPage", pageMovies.getNumber());
        response.put("totalItem", pageMovies.getTotalElements());
        response.put("totalPage", pageMovies.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<?> getMovieBySearch(@RequestParam String keyword, @RequestParam int index,
                                              @RequestParam int size) {
        List<Movie> list = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        Page<Movie> pageMovies= movieService.getAllMovieBySearch(keyword,index,size);
        list = pageMovies.getContent();
        response.put("movie", list);
        response.put("currentPage", pageMovies.getNumber());
        response.put("totalItem", pageMovies.getTotalElements());
        response.put("totalPage", pageMovies.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "getmovieby")
    public ResponseEntity<?> getMovieByType(@RequestParam Long typeId) {
        return new ResponseEntity<>(movieService.getMovieByType(typeId), HttpStatus.OK);
    }

    @PostMapping("addMovie")
    public ResponseEntity<?> addMovie(@RequestBody MovieAddDTO movie,
                                      @RequestParam(required = false, name = "cinemaId") String cinemaId) {
        Map<String, Object> response = new HashMap<>();
        try {
            movieService.checkMovie(movie, cinemaId);
            response.put("message", "Create successfully!!!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("message", "Duplicate show date!!!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("detail")
    public ResponseEntity<?> getMovieByID(@RequestParam Integer movieID) {

        Movie movie = movieService.findMovieByID(movieID);

        return new ResponseEntity<>(movie, HttpStatus.OK);

    }

}