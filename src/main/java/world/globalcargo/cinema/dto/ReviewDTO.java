package world.globalcargo.cinema.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ReviewDTO {
    private Long id;
    private String authorName;
    private String text;
    private int rating;
    private Long movieId;
}