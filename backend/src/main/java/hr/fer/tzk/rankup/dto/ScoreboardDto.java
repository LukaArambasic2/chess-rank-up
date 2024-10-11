package hr.fer.tzk.rankup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreboardDto {
    private String firstName;
    private String lastName;
    private int points;
}
