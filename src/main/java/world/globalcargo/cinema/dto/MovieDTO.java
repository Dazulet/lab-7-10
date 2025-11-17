package world.globalcargo.cinema.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private int duration;
    private Set<GenreDTO> genres;
}