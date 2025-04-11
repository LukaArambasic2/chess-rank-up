package hr.fer.tzk.rankup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileGeneralInfoDto {
    private String firstName;
    private String lastName;
    private String jmbag;
    private String rankName;
    private int pointsSemester;
    private int additionalPointsNeeded;
    private int pointsTotal;
}
