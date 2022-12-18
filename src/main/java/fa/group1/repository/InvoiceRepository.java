package fa.group1.repository;

import fa.group1.entities.Invoice;
import fa.group1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
    @Query(value = "select * from account a inner join Ticket b on a.account_id=b.account_id inner join Invoice c on b.ticket_id=c.ticket_id where a.account_id=?1",nativeQuery = true)
    List<Invoice> getHis(@Param("accountId") Integer id);
}
