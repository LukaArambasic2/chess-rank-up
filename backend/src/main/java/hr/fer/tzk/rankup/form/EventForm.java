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
    @NotBlank(message = "Ime eventa ne smije biti prazno!")
    @Size(max = 30)
    private String name;

    @NotNull(message = "Unesite datum eventa!")
    private LocalDate date; // TODO: validator za datum

    @NotBlank(message = "Opis eventa ne smije biti prazan!")
    @Size(max = 80)
    private String description;

    private Long idSection;

    private Long idEventType;
}
