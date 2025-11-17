package world.globalcargo.cinema.service;

import world.globalcargo.cinema.dto.GenreDTO;
import java.util.List;

public interface GenreServiceInterface {
    List<GenreDTO> getAllGenres();
    GenreDTO getGenreById(Long id);
    GenreDTO addGenre(GenreDTO genreDto);
    GenreDTO updateGenre(Long id, GenreDTO genreDto);
    void deleteGenre(Long id);
}