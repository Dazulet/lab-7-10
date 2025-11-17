package world.globalcargo.cinema.mappers;

import org.mapstruct.Mapper;
import world.globalcargo.cinema.dto.GenreDTO;
import world.globalcargo.cinema.entite.Genre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDTO toDto(Genre genre);
    Genre toEntity(GenreDTO genreDTO);
    List<GenreDTO> toDtoList(List<Genre> genres);

}