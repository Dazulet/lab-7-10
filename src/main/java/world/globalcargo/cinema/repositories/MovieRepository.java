package world.globalcargo.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.globalcargo.cinema.entite.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {}