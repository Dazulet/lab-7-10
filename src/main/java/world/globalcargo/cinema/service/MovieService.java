package world.globalcargo.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.globalcargo.cinema.dto.MovieDTO;
import world.globalcargo.cinema.dto.MovieScheduleDTO;
import world.globalcargo.cinema.entite.Movie;
import world.globalcargo.cinema.entite.Session;
import world.globalcargo.cinema.repositories.MovieRepository;
import world.globalcargo.cinema.service.MoviesServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService implements MoviesServiceInterface{
    private final MovieRepository movieRepository;

    @Override
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDtoList = new ArrayList<>();
        movies.forEach(movie -> {
            MovieDTO dto = toDto(movie);
            movieDtoList.add(dto);
        });
        return movieDtoList;
    }

    @Override
    public MovieDTO getMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie==null) {
            return null;
        }
        return toDto(movie);
    }

    @Override
    public MovieDTO addMovie(MovieDTO movieDto) {
        Movie movie = toEntity(movieDto);
        Movie createdMovie = movieRepository.save(movie);
        return toDto(createdMovie);
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDto) {
        MovieDTO checkMovie = getMovie(id);
        if (checkMovie==null) {
            return null;
        }
        Movie movie = toEntity(movieDto);
        movie.setId(id);
        Movie updatedMovie = movieRepository.save(movie);
        return toDto(updatedMovie);
    }

    @Override
    public boolean deleteMovie(Long id) {
        MovieDTO checkMovie = getMovie(id);
        if (checkMovie==null) {
            return false;
        }
        movieRepository.deleteById(id);
        return true;
    }

    @Override
    public MovieScheduleDTO getMovieSchedule(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie==null) {
            return null;
        }
        return toMovieScheduleDTO(movie);
    }

    private MovieDTO toDto(Movie movie) {
        return MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .duration(movie.getDuration())
                .build();
    }

    private Movie toEntity(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setDuration(dto.getDuration());
        return movie;
    }

    private MovieScheduleDTO toMovieScheduleDTO(Movie movie) {
        List<Session> sessions = movie.getSessions();
        List<MovieScheduleDTO.SessionInfo> schedule = new ArrayList<>();
        sessions.forEach(session -> {
            MovieScheduleDTO.SessionInfo sessionInfo = MovieScheduleDTO.SessionInfo
                    .builder()
                    .sessionId(session.getId())
                    .startTime(session.getStartTime())
                    .hallNumber(session.getHallNumber())
                    .price(session.getPrice())
                    .build();

            schedule.add(sessionInfo);
        });

        return MovieScheduleDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .duration(movie.getDuration())
                .schedule(schedule)
                .build();
    }

}
