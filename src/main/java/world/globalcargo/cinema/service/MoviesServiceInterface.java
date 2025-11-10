package world.globalcargo.cinema.service;

import world.globalcargo.cinema.dto.MovieDTO;
import world.globalcargo.cinema.dto.MovieScheduleDTO;
import world.globalcargo.cinema.dto.*;
import java.util.List;
import java.util.List;

public interface MoviesServiceInterface {
    List<MovieDTO> getAllMovies();
    MovieDTO getMovie(Long id);
    MovieDTO addMovie(MovieDTO movie);
    MovieDTO updateMovie(Long id, MovieDTO movie);
    boolean deleteMovie(Long id);
    MovieScheduleDTO getMovieSchedule(Long id);

}
