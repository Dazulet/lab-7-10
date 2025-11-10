package world.globalcargo.cinema.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private int duration;
}