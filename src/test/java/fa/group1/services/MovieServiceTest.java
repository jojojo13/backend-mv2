package fa.group1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fa.group1.dto.MovieAddDTO;
import fa.group1.dto.MovieByTypeDTO;
import fa.group1.entities.CinemaRoom;
import fa.group1.entities.Movie;
import fa.group1.entities.Schedule;
import fa.group1.entities.ScheduleMovie;
import fa.group1.entities.Type;
import fa.group1.exceptions.ResourceNotFoundException;
import fa.group1.repository.CinemaRoomRepository;
import fa.group1.repository.MovieRepository;
import fa.group1.repository.ScheduleMovieRepository;
import fa.group1.repository.ScheduleRepository;
import fa.group1.repository.TypeRepository;
import fa.group1.services.impl.MovieServiceImpl;
import fa.group1.services.impl.ScheduleMovieServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private CinemaRoomRepository cinemaRoomRepository;
    @Mock
    private ScheduleMovieRepository scheduleMovieRepository;

    @Mock
    private TypeRepository typeRepository;

    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    @InjectMocks
    private ScheduleMovieServiceImpl scheduleMovieService;
    private Movie movie;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        movie = new Movie();
    }

    @Test
    public void findMovieByIDTest() {
        movie.setMovieId(1);
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        Optional<Movie> resultOpt = Optional.of(movieServiceImpl.findMovieByID(1));
        Movie result = resultOpt.get();
        assertEquals(1, result.getMovieId());

    }


    @Test
    public void getMovieByTypeTest() {
        List<Movie> list = new ArrayList<>();
        List<Type> listType = new ArrayList<>();
        Type type = new Type();
        type.setTypeId(1);
        listType.add(type);
        movie.setTypes(listType);
        list.add(movie);
        list.add(movie);
        list.add(movie);

        MovieByTypeDTO movieByTypeDTO = new MovieByTypeDTO();
        when(movieRepository.getAll(3, 1L)).thenReturn(list);
        when(modelMapper.map(movie, MovieByTypeDTO.class)).thenReturn(movieByTypeDTO);

        List<MovieByTypeDTO> result = movieServiceImpl.getMovieByType(1L);
        Assert.assertEquals(3, result.size());
    }


    @Test
    public void getAllMovieByPagingTest() {
        Pageable pageable = PageRequest.of(0, 3);
        List<Movie> list = new ArrayList<>();
        list.add(movie);
        Page<Movie> pageMovie = new PageImpl<>(list);
        when(movieRepository.findAll(pageable)).thenReturn(pageMovie);
        Page<Movie> result = movieServiceImpl.getAllMovieByPaging(0, 3);
        Assert.assertEquals(0, result.getNumber());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAllMovieByPagingEmptyTestEmpty() {
        Pageable pageable = PageRequest.of(0, 3);
        List<Movie> listEmpty = new ArrayList<>();
        Page<Movie> pageEmpty = new PageImpl<>(listEmpty);
        when(movieRepository.findAll(pageable)).thenReturn(pageEmpty);
        Page<Movie> result = movieServiceImpl.getAllMovieByPaging(0, 3);
        Assert.assertEquals(0, result.isEmpty());
    }

    @Test
    public void getAllMovieBySearchTestNoEmpty() {
        Pageable pageable = PageRequest.of(0, 3,
                Sort.by("movie_name_english").descending().and(Sort.by("movie_name_vn").descending()));
        List<Movie> list = new ArrayList<>();
        movie.setMovieId(1);
        movie.setMovie_name_vn("tieng viet");
        movie.setMovie_name_english("english");
        list.add(movie);

        Page<Movie> pageMovie = new PageImpl<>(list);
        when(movieRepository.findMovieByName("tieng", pageable)).thenReturn(pageMovie);
        Page<Movie> result = movieServiceImpl.getAllMovieBySearch("tieng", 0, 3);
        Assert.assertEquals(0, result.getNumber());
    }

    @Test
    public void getAllMovieBySearchTestEmpty() {
        Pageable pageable = PageRequest.of(0, 3);
        List<Movie> list = new ArrayList<>();
        movie.setMovieId(1);
        movie.setMovie_name_vn("tieng viet");
        movie.setMovie_name_english("english");
        list.add(movie);

        Page<Movie> pageMovie = new PageImpl<>(list);

        when(movieRepository.findAll(pageable)).thenReturn(pageMovie);
        Page<Movie> result = movieServiceImpl.getAllMovieBySearch("", 0, 3);
        System.out.println(list);
        System.out.println("------------------");
        Assert.assertEquals(0, result.getNumber());
    }

    @Test
    public void CheckMovie() {
        List<ScheduleMovie> scheduleMovieList = new ArrayList<>();
        scheduleMovieList.add(new ScheduleMovie());
        List<Integer> typeListId = new ArrayList<>();
        typeListId.add(1);
        List<Integer> scheduleListId = new ArrayList<>();
        scheduleListId.add(1);
        MovieAddDTO movieDTO = new MovieAddDTO();
        movieDTO.setMovieId(1);
        Movie movie = new Movie();
        movie.setMovieId(1);
        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setCinemaRoomId(1);

        movieDTO.setListTypes(typeListId);
        movieDTO.setListSchedule(scheduleListId);
        movieDTO.setFrom_date(LocalDate.of(2011, 1, 1));
        movieDTO.setTo_date(LocalDate.of(2011, 2, 1));
        movie.setFrom_date(LocalDate.of(2011, 1, 1));
        movie.setTo_date(LocalDate.of(2011, 2, 1));


        Mockito.when(typeRepository.findById(1)).thenReturn(Optional.of(new Type()));
        Mockito.when(modelMapper.map(movieDTO, Movie.class)
        ).thenReturn(movie);
        Mockito.when(cinemaRoomRepository.findById(1)
        ).thenReturn(Optional.of(cinemaRoom));
        Mockito.when(scheduleRepository.findById(1)
        ).thenReturn(Optional.of(new Schedule()));
//        Mockito.when(movieRepository.save(movie)
//        ).thenReturn(movie);
//        Mockito.when(scheduleMovieService.saveAll(scheduleMovieList)
//        ).thenReturn(scheduleMovieList);

        assertNotNull(movieServiceImpl.checkMovie(movieDTO, "1"));
    }
}

