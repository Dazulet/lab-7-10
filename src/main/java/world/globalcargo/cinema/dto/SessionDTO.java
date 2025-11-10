package world.globalcargo.cinema.dto;

import lombok.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    private Long id;
    private Date startTime;
    private int hallNumber;
    private double price;
    private Long movieId;
}