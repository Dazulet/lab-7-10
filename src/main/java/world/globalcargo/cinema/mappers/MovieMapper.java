package world.globalcargo.cinema.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.globalcargo.cinema.dto.MovieDTO;
import world.globalcargo.cinema.entite.Genre;
import world.globalcargo.cinema.entite.Movie;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface MovieMapper {


    MovieDTO toDto(Movie movie);


    @Mapping(target = "genres", ignore = true)
    Movie toEntity(MovieDTO movieDTO);

    List<MovieDTO> toDtoList(List<Movie> movies);
}