package world.globalcargo.cinema.mappers;

import org.mapstruct.Mapper;
import world.globalcargo.cinema.dto.MovieDTO;
import world.globalcargo.cinema.entite.Movie;

import java.util.List;

// Говорим MapStruct, что для маппинга поля 'genres' нужно использовать GenreMapper
@Mapper(componentModel = "spring", uses = {GenreMapper.class})
public interface MovieMapper {
    MovieDTO toDto(Movie movie);
    Movie toEntity(MovieDTO movieDTO);
    List<MovieDTO> toDtoList(List<Movie> movies);
}