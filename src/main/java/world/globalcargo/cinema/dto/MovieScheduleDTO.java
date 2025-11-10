package world.globalcargo.cinema.dto;

import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieScheduleDTO {
    private Long id;
    private String title;
    private int duration;
    private List<SessionInfo> schedule;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SessionInfo {
        private Long sessionId;
        private Date startTime;
        private int hallNumber;
        private double price;
    }
}