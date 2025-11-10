package world.globalcargo.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.globalcargo.cinema.entite.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {}