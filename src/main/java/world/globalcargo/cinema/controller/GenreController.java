package world.globalcargo.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.globalcargo.cinema.dto.GenreDTO;
import world.globalcargo.cinema.service.GenreServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreServiceInterface genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genres = genreService.getAllGenres();
        if (genres.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        GenreDTO genre = genreService.getGenreById(id);
        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(genre);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genreDto) {
        GenreDTO createdGenre = genreService.addGenre(genreDto);
        return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, @RequestBody GenreDTO genreDto) {
        GenreDTO updatedGenre = genreService.updateGenre(id, genreDto);
        if (updatedGenre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        if (genreService.getGenreById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}