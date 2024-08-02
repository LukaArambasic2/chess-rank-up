package hr.fer.tzk.rankup.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeForm {
    @NotBlank(message = "Ime tipa eventa ne smije biti prazno!")
    @Size(max = 30)
    private String name;

    @Value("0")
    @Min(value = 0, message = "Broj bodova mora biti pozitivan!")
    @Max(10) // TODO: staviti neki limit na max bodove
    private int defaultPoints;
}
