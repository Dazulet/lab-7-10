package world.globalcargo.cinema.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.globalcargo.cinema.dto.TicketDTO;
import world.globalcargo.cinema.service.TicketService;
import world.globalcargo.cinema.service.TicketServiceInterface;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketServiceInterface ticketService;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> tickets = ticketService.getAllTickets();
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id) {
        TicketDTO ticket = ticketService.getTicket(id);
        if (Objects.isNull(ticket)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> addTicket(@RequestBody TicketDTO ticketDto) {
        TicketDTO createdTicket = ticketService.addTicket(ticketDto);
        if (Objects.isNull(createdTicket)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        boolean isDeleted = ticketService.deleteTicket(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
