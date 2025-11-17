package world.globalcargo.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.globalcargo.cinema.dto.GenreDTO;
import world.globalcargo.cinema.entite.Genre;
import world.globalcargo.cinema.mappers.GenreMapper;
import world.globalcargo.cinema.repositories.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService implements GenreServiceInterface {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreMapper.toDtoList(genreRepository.findAll());
    }

    @Override
    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre != null) {
            return genreMapper.toDto(genre);
        } else {
            return null;
        }
    }

    @Override
    public GenreDTO addGenre(GenreDTO genreDto) {
        Genre genre = genreMapper.toEntity(genreDto);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.toDto(savedGenre);
    }

    @Override
    public GenreDTO updateGenre(Long id, GenreDTO genreDto) {

        Genre existingGenre = genreRepository.findById(id).orElse(null);

        if (existingGenre != null) {
            Genre genreToUpdate = genreMapper.toEntity(genreDto);
            genreToUpdate.setId(id);
            Genre updatedGenre = genreRepository.save(genreToUpdate);
            return genreMapper.toDto(updatedGenre);
        } else {
            return null;
        }
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}