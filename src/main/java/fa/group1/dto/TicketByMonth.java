package fa.group1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TicketByMonth {
    @Id
    private String month;
    private Integer total;
}
