package hr.fer.tzk.rankup.form;

import hr.fer.tzk.rankup.validation.ValidJmbag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionMemberForm {
    @NotBlank(message = "JMBAG is missing")
    @ValidJmbag
    private String jmbag;

    @NotBlank(message = "Rank name is missing")
    private String rankName;
}
