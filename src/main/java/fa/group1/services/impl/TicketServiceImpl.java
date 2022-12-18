package fa.group1.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import fa.group1.entities.User;
import fa.group1.exceptions.ResourceNotFoundException;
import fa.group1.utils.TokenAuthenticationUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import fa.group1.dto.BookedTicketDTO;
import fa.group1.entities.Ticket;
import fa.group1.repository.TicketRepository;
import fa.group1.repository.UserRepository;
import fa.group1.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Object> findAllTicket() {
        return ticketRepository.findAllTicket();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public List<BookedTicketDTO> getAllBookedTicketDTOs(String token) {
        Authentication authentication = TokenAuthenticationUtils.getAuthentication(token);
        Optional<User> u = userRepository.findByUsername(authentication.getPrincipal().toString());
        if (!u.isPresent()) {
            LOGGER.error("User not found");
            throw new ResourceNotFoundException("User not found");
        }
        return ticketRepository.getAllBookedTicket(u.get().getAccountId());
    }

    @Override
    public Ticket getTicketByID(Integer id) {
        return ticketRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Ticket not found");
        });
    }

    @Override
    public Ticket updateConfirmTicket(Integer ticketID) {
        Ticket t=ticketRepository.findById(ticketID).orElseThrow(() -> {
            throw new ResourceNotFoundException("Ticket not found");
        });
        t.setTicketType(1);
        return ticketRepository.save(t);
    }

}
