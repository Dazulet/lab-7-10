package world.globalcargo.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.globalcargo.cinema.dto.MovieDTO;
import world.globalcargo.cinema.dto.MovieScheduleDTO;
import world.globalcargo.cinema.service.MovieService;
import world.globalcargo.cinema.service.MoviesServiceInterface;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MoviesServiceInterface movieService;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movies = movieService.getAllMovies();
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        MovieDTO movieId = movieService.getMovie(id);
        if (Objects.isNull(movieId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(movieId);
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<MovieScheduleDTO> getMovieSchedule(@PathVariable Long id) {
        MovieScheduleDTO schedule = movieService.getMovieSchedule(id);
        if (Objects.isNull(schedule)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(schedule);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movieDto) {
        MovieDTO createdMovie = movieService.addMovie(movieDto);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDto) {
        MovieDTO updatedMovie = movieService.updateMovie(id, movieDto);
        if (Objects.isNull(updatedMovie)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        boolean isDeleted = movieService.deleteMovie(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}