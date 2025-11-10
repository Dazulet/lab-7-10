package world.globalcargo.cinema.service;
import world.globalcargo.cinema.dto.SessionDTO;
import java.util.List;

public interface SessionServiceInterface {
    List<SessionDTO> getAllSessions();

    SessionDTO getSession(Long id);

    SessionDTO addSession(SessionDTO session);

    SessionDTO updateSession(Long id, SessionDTO session);

    boolean deleteSession(Long id);
}
