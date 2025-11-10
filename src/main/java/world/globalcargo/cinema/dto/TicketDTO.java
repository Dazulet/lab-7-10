package world.globalcargo.cinema.dto;

import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long id;
    private Long sessionId;
    private Long userId;
    private int seatNumber;
}