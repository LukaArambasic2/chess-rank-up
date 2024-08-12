package hr.fer.tzk.rankup.form;

import hr.fer.tzk.rankup.validation.ValidJmbag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicMemberForm {
    @NotBlank(message = "First name is missing")
    @Size(max = 30, message = "First name too long")
    private String firstName;

    @NotBlank(message = "Last name is missing")
    @Size(max = 30, message = "Last name too long")
    private String lastName;

    @NotBlank(message = "JMBAG is missing")
    @ValidJmbag
    private String jmbag;
}
