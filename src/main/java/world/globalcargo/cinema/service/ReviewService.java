package world.globalcargo.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.globalcargo.cinema.dto.ReviewDTO;
import world.globalcargo.cinema.entite.Movie;
import world.globalcargo.cinema.entite.Review;
import world.globalcargo.cinema.mappers.ReviewMapper;
import world.globalcargo.cinema.repositories.MovieRepository;
import world.globalcargo.cinema.repositories.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements ReviewServiceInterface {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDTO> getReviewsByMovie(Long movieId) {
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        return reviewMapper.toDtoList(reviews);
    }

    @Override
    public ReviewDTO addReviewToMovie(Long movieId, ReviewDTO reviewDTO) {
        Movie movie = movieRepository.findById(movieId).orElse(null);

        if (movie == null) {
            return null;
        }

        Review review = reviewMapper.toEntity(reviewDTO);
        review.setMovie(movie);

        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }
}
