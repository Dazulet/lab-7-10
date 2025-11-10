package world.globalcargo.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.globalcargo.cinema.entite.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {}