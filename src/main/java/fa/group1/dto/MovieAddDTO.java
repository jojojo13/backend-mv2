package fa.group1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieAddDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer movieId;

    private String actor;

    private String content;

    private String director;

    private Double duration;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate from_date;

    private String movie_production_company;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate to_date;

    private String version;

    private String movie_name_english;

    private String movie_name_vn;

    private String large_image;

    private String small_image;
    private List<Integer> listSchedule;
    private List<Integer> listTypes;
    private String previewUrl;
}
