package hr.fer.tzk.rankup.form;

import hr.fer.tzk.rankup.validation.ValidEmail;
import hr.fer.tzk.rankup.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @NotBlank(message = "Email is missing")
    @Size(max = 50, message = "Email too long")
    @ValidEmail
    private String email;

    @NotBlank(message = "Password is missing")
    @Size(min = 8, max = 30, message = "Password too short or too long")
    @ValidPassword
    private String password;
}
