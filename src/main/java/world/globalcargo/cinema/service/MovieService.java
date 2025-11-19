package world.globalcargo.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.globalcargo.cinema.dto.MovieDTO;
import world.globalcargo.cinema.dto.MovieScheduleDTO;
import world.globalcargo.cinema.entite.Genre;
import world.globalcargo.cinema.entite.Movie;
import world.globalcargo.cinema.entite.Session;
import world.globalcargo.cinema.mappers.MovieMapper;
import world.globalcargo.cinema.repositories.GenreRepository;
import world.globalcargo.cinema.repositories.MovieRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieService implements MoviesServiceInterface {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final GenreRepository genreRepository;

    @Override
    public List<MovieDTO> getAllMovies() {
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    @Override
    public MovieDTO getMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie == null) {
            return null;
        }
        return movieMapper.toDto(movie);
    }

    @Override
    public MovieDTO addMovie(MovieDTO movieDto) {
        Movie movie = movieMapper.toEntity(movieDto);
        assignGenresToMovie(movie, movieDto);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDto(savedMovie);
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDto) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);

        if (existingMovie == null) {
            return null;
        }

        existingMovie.setTitle(movieDto.getTitle());
        existingMovie.setDescription(movieDto.getDescription());
        existingMovie.setDuration(movieDto.getDuration());

        assignGenresToMovie(existingMovie, movieDto);

        Movie updatedMovie = movieRepository.save(existingMovie);
        return movieMapper.toDto(updatedMovie);
    }


    private void assignGenresToMovie(Movie movie, MovieDTO movieDto) {
        if (movieDto.getGenres() == null || movieDto.getGenres().isEmpty()) {
            movie.setGenres(new HashSet<>());
            return;
        }

        List<Long> genreIds = new ArrayList<>();
        for (world.globalcargo.cinema.dto.GenreDTO genreDto : movieDto.getGenres()) {
            genreIds.add(genreDto.getId());
        }

        List<Genre> foundGenres = genreRepository.findAllById(genreIds);
        movie.setGenres(new HashSet<>(foundGenres));
    }

    @Override
    public boolean deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MovieScheduleDTO getMovieSchedule(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return null;
        }
        return toMovieScheduleDTO(movie);
    }

    private MovieScheduleDTO toMovieScheduleDTO(Movie movie) {
        List<MovieScheduleDTO.SessionInfo> schedule = new ArrayList<>();
        for (Session session : movie.getSessions()) {
            MovieScheduleDTO.SessionInfo sessionInfo = MovieScheduleDTO.SessionInfo.builder()
                    .sessionId(session.getId())
                    .startTime(session.getStartTime())
                    .hallNumber(session.getHallNumber())
                    .price(session.getPrice())
                    .build();
            schedule.add(sessionInfo);
        }

        return MovieScheduleDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .duration(movie.getDuration())
                .schedule(schedule)
                .build();
    }
}