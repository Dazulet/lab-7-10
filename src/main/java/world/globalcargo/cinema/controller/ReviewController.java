package world.globalcargo.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.globalcargo.cinema.dto.ReviewDTO;
import world.globalcargo.cinema.service.ReviewServiceInterface;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewServiceInterface reviewService;

    @GetMapping("/movies/{movieId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getMovieReviews(@PathVariable Long movieId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByMovie(movieId);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<ReviewDTO> addMovieReview(@PathVariable Long movieId, @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.addReviewToMovie(movieId, reviewDTO);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }
}