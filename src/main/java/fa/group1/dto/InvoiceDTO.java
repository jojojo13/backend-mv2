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
public class InvoiceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer addCore;

    private LocalDate bookingDate;

    private Integer movieName;

    private Integer useScore;

    private Integer totalMoney;

    private Integer status;
}
