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
    @NotBlank(message = "Name is missing")
    @Size(max = 30)
    private String name;

    @NotNull(message = "Date is missing")
    private LocalDate date; // TODO: validator za datum

    @NotBlank(message = "Description is missing")
    @Size(max = 80)
    private String description;

    @NotNull
    private Long idEventType;
}
