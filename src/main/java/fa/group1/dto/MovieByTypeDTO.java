package fa.group1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieByTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer movieId;

    private String actor;

    private String content;

    private String director;


    private Double duration;


    private LocalDate from_date;


    private String movie_production_company;


    private LocalDate to_date;


    private String version;


    private String movie_name_english;


    private String movie_name_vn;


    private String large_image;


    private String small_image;
}
