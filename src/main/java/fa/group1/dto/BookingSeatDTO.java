package fa.group1.dto;

import fa.group1.entities.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingSeatDTO {
    private Double price;
    private List<Seat> listSeat;
}
