package world.globalcargo.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.globalcargo.cinema.dto.TicketDTO;
import world.globalcargo.cinema.entite.Session;
import world.globalcargo.cinema.entite.Ticket;
import world.globalcargo.cinema.entite.User;
import world.globalcargo.cinema.repositories.SessionRepository;
import world.globalcargo.cinema.repositories.TicketRepository;
import world.globalcargo.cinema.repositories.UserRepository;
import world.globalcargo.cinema.service.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TicketService implements TicketServiceInterface{
    private final TicketRepository ticketRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDTO> dtoList = new ArrayList<>();
        tickets.forEach(ticket -> dtoList.add(toDto(ticket)));
        return dtoList;
    }

    @Override
    public TicketDTO getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if ((ticket)==null) {
            return null;
        }
        return toDto(ticket);
    }

    @Override
    public TicketDTO addTicket(TicketDTO ticketDto) {
        Session session = sessionRepository.findById(ticketDto.getSessionId()).orElse(null);
        User user = userRepository.findById(ticketDto.getUserId()).orElse(null);
        if ((session)==null || (user)==null) {
            return null;
        }
        Ticket ticket = toEntity(ticketDto);
        ticket.setSession(session);
        ticket.setUser(user);
        Ticket createdTicket = ticketRepository.save(ticket);
        return toDto(createdTicket);
    }

    @Override
    public boolean deleteTicket(Long id) {
        if ((getTicket(id))==null) {
            return false;
        }
        ticketRepository.deleteById(id);
        return true;
    }

    private TicketDTO toDto(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .sessionId(ticket.getSession().getId())
                .userId(ticket.getUser().getId())
                .seatNumber(ticket.getSeatNumber())
                .build();
    }

    private Ticket toEntity(TicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setSeatNumber(dto.getSeatNumber());
        return ticket;
    }

}
