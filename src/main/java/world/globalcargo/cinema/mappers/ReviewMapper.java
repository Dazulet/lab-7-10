package world.globalcargo.cinema.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.globalcargo.cinema.dto.ReviewDTO;
import world.globalcargo.cinema.entite.Review;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "movie.id", target = "movieId")
    ReviewDTO toDto(Review review);


    @Mapping(target = "movie", ignore = true)
    Review toEntity(ReviewDTO reviewDTO);

    List<ReviewDTO> toDtoList(List<Review> reviews);
}