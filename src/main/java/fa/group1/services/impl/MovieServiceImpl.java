package fa.group1.services.impl;

import fa.group1.dto.MovieAddDTO;
import fa.group1.dto.MovieByTypeDTO;
import fa.group1.dto.UserDTO;
import fa.group1.entities.*;
import fa.group1.exceptions.ResourceNotFoundException;
import fa.group1.repository.*;
import fa.group1.services.MovieService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScheduleMovieRepository scheduleMovieRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Override
    public Movie findMovieByID(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> { 
                	LOGGER.error("Movie not found");
                	throw new ResourceNotFoundException("Movie not found");});
    }

    @Override
    public Page<Movie> getAllMovieBySearch(String searchTxt, int page, int size) {

        Page<Movie> pageMovies;

        Pageable sortedByName = PageRequest.of(page, size,
                Sort.by("movie_name_english").descending().and(Sort.by("movie_name_vn").descending()));
        ;
        if (searchTxt.isEmpty()) {
            Pageable paging = PageRequest.of(page, size);
            pageMovies = movieRepository.findAll(paging);
        } else {
            pageMovies = movieRepository.findMovieByName(searchTxt, sortedByName);
        }

        return pageMovies;

    }

    @Override
    public List<MovieByTypeDTO> getMovieByType(Long typeID) {
        List<MovieByTypeDTO> movieDTOList = movieRepository.getAll(3, typeID)
                .stream()
                .map(movie -> modelMapper.map(movie, MovieByTypeDTO.class))
                .collect(Collectors.toList());
        return movieDTOList;
    }

    @Override
    public Page<Movie> getAllMovieByPaging(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Movie> pageMovies = movieRepository.findAll(paging);
        if (pageMovies.getContent().isEmpty()) {
        	LOGGER.error("Movie is empty");
            throw new ResourceNotFoundException("Movie is empty");
        }


        return pageMovies;

    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public Movie checkMovie(MovieAddDTO movieDTO, String cinemaRoomId) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        List<Type> listType = new ArrayList<>();
        movieDTO.getListTypes().forEach(id -> {
            Type type = typeRepository.findById(id).get();
            listType.add(type);
        });
        movie.setTypes(listType);

        LocalDate start = movieDTO.getFrom_date();
        LocalDate end = movieDTO.getTo_date();

        CinemaRoom cinemaRoom = cinemaRoomRepository.getById(Integer.valueOf(cinemaRoomId));

        Stream.iterate(start, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(start, end) + 1)
                .forEach(t -> {
                    movieRepository.save(movie);
                    movieDTO.getListSchedule().forEach(scheduleId -> {
                        Schedule schedule = scheduleRepository.getById(Integer.valueOf(scheduleId));
                        ScheduleMovie scheduleMovie = new ScheduleMovie();
                        scheduleMovie.setSchedule(schedule);
                        scheduleMovie.setDate(t);
                        scheduleMovie.setCinemaRoom(cinemaRoom);
                        scheduleMovie.setMovie(movie);
                        scheduleMovieRepository.save(scheduleMovie);
                    });
                });

        return movie;
    }


}
