package world.globalcargo.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.globalcargo.cinema.dto.SessionDTO;
import world.globalcargo.cinema.entite.Movie;
import world.globalcargo.cinema.entite.Session;
import world.globalcargo.cinema.repositories.MovieRepository;
import world.globalcargo.cinema.repositories.SessionRepository;
import world.globalcargo.cinema.service.SessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SessionService implements SessionServiceInterface{
    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;

    @Override
    public List<SessionDTO> getAllSessions() {
        List<Session> sessions = sessionRepository.findAll();
        List<SessionDTO> dtoList = new ArrayList<>();
        sessions.forEach(session -> dtoList.add(toDto(session)));
        return dtoList;
    }

    @Override
    public SessionDTO getSession(Long id) {
        Session session = sessionRepository.findById(id).orElse(null);
        if (Objects.isNull(session)) {
            return null;
        }
        return toDto(session);
    }

    @Override
    public SessionDTO addSession(SessionDTO sessionDto) {
        Movie movie = movieRepository.findById(sessionDto.getMovieId()).orElse(null);
        if (Objects.isNull(movie)) {
            return null;
        }
        Session session = toEntity(sessionDto);
        session.setMovie(movie);
        Session createdSession = sessionRepository.save(session);
        return toDto(createdSession);
    }

    @Override
    public SessionDTO updateSession(Long id, SessionDTO sessionDto) {
        if (Objects.isNull(getSession(id))) {
            return null;
        }
        Movie movie = movieRepository.findById(sessionDto.getMovieId()).orElse(null);
        if (Objects.isNull(movie)) {
            return null;
        }
        Session session = toEntity(sessionDto);
        session.setId(id);
        session.setMovie(movie);
        Session updatedSession = sessionRepository.save(session);
        return toDto(updatedSession);
    }

    @Override
    public boolean deleteSession(Long id) {
        if (Objects.isNull(getSession(id))) {
            return false;
        }
        sessionRepository.deleteById(id);
        return true;
    }private SessionDTO toDto(Session session) {
        return SessionDTO.builder()
                .id(session.getId())
                .startTime(session.getStartTime())
                .hallNumber(session.getHallNumber())
                .price(session.getPrice())
                .movieId(session.getMovie().getId())
                .build();
    }

    private Session toEntity(SessionDTO dto) {
        Session session = new Session();
        session.setStartTime(dto.getStartTime());
        session.setHallNumber(dto.getHallNumber());
        session.setPrice(dto.getPrice());
        return session;
    }

}
