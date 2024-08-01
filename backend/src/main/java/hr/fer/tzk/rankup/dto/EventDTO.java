package hr.fer.tzk.rankup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private String eventName;
    private LocalDate date;
    private String description;
    private String eventTypeName;
}
