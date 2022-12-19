package fa.group1.repository;

import fa.group1.dto.TicketByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonthRepository extends JpaRepository<TicketByMonth,String>{
    @Query(value = "declare @Months table ( Month int)\n" +
            "insert into @Months values (1), (2), (3) ,(4),(5),(6),(7),(8),(9),(10),(11),(12)\n" +
            "select M.Month, count(DATEPART(month,t.booking_date)) as total\n" +
            "from @Months M\n" +
            " left join ticket as T\n" +
            "    on M.Month = DATEPART(month,t.booking_date)\n" +
            "\n" +
            "group by M.Month",nativeQuery = true)
    List<TicketByMonth> getTotalTicket();
}
