package world.globalcargo.cinema.service;

import world.globalcargo.cinema.dto.ReviewDTO;
import java.util.List;

public interface ReviewServiceInterface{
    List<ReviewDTO> getReviewsByMovie(Long movieId);
    ReviewDTO addReviewToMovie(Long movieId, ReviewDTO reviewDTO);

}
