package world.globalcargo.cinema.service;

import world.globalcargo.cinema.dto.TicketDTO;

import java.util.List;

public interface TicketServiceInterface {
    List<TicketDTO> getAllTickets();
    TicketDTO getTicket(Long id);
    TicketDTO addTicket(TicketDTO ticket);
    boolean deleteTicket(Long id);
}
