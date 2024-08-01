package hr.fer.tzk.rankup.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventForm {
    @NotBlank(message = "Svi atributi moraju biti popunjeni")
    @Size(max = 30)
    private String name;

    @NotNull(message = "Svi atributi moraju biti popunjeni")
    private LocalDate date; // TODO: validator za datum

    @NotBlank(message = "Svi atributi moraju biti popunjeni")
    @Size(max = 80)
    private String description;

    private Long idSection;

    private Long idEventType;
}
