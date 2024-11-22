package hr.fer.tzk.rankup.form;

import hr.fer.tzk.rankup.validation.ValidEmail;
import hr.fer.tzk.rankup.validation.ValidJmbag;
import hr.fer.tzk.rankup.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
    @NotBlank(message = "First name is missing")
    @Size(max = 30, message = "First name too long")
    private String firstName;

    @NotBlank(message = "Last name is missing")
    @Size(max = 30, message = "Last name too long")
    private String lastName;

    @NotBlank(message = "Email is missing")
    @Size(max = 50, message = "Email too long")
    @ValidEmail
    private String email;

    @NotBlank(message = "Password is missing")
    @Size(min = 8, max = 30, message = "Password too short or too long")
    @ValidPassword
    private String password;

    @NotBlank(message = "Repeat password is missing")
    @Size(min = 8, max = 30, message = "Repeat password too short or too long")
    @ValidPassword
    private String repeatPassword;

    @NotBlank(message = "JMBAG is missing")
    @ValidJmbag
    private String jmbag;
}
