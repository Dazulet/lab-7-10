package world.globalcargo.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.globalcargo.cinema.entite.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}