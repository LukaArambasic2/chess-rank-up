package hr.fer.tzk.rankup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailedMemberDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String jmbag;
    private String email;
}
