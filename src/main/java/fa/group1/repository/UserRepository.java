package fa.group1.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.group1.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username,String password);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query(value = "select * from Account inner join Role on Account.role_id=Role.role_id where Account.role_id=2",nativeQuery = true)
    List<User> getAllEmployee();


    Optional<User> findByEmail(String email);
    Optional<User> findByIdentityCard(String identityCard);
    @Query(value = "select * from Account inner join Ticket on Account.account_id=Ticket.ticket_id inner join Invoice on Ticket.ticket_id=Invoice.ticket_id",nativeQuery = true)
    List<User> getHis();

    @Query(value = "select * from Account where account_id=?1",nativeQuery = true)
    User getAllByIdUser(@Param("id") Integer id);

}
