package fa.group1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.group1.entities.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	@Query(value = "select distinct M from Movie M where M.movie_name_english like %:title% or M.movie_name_vn like %:title%")
	Page<Movie> findMovieByName(@Param("title") String title,Pageable page);

	@Query(value = "select top(?1) * from Type inner join movie_type on Type.type_id=movie_type.type_id inner join MOVIE on movie_type.moive_id=MOVIE.movie_id where TYPE.type_id = ?2",nativeQuery = true)
	List<Movie> getAll(Integer limit, @Param("typeId") Long typeId);
}
