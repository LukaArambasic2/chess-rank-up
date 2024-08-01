package hr.fer.tzk.rankup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionMemberDto {
    private String firstName;
    private String lastName;
    private String jmbag;
    private String email;
    private boolean active;
    private int pointsTotal;
    private String rank;
}
