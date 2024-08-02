package hr.fer.tzk.rankup.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDatesForm {

    @NotNull(message = "Unesite oba datuma!")
    private LocalDate dateFrom;

    @NotNull(message = "Unesite oba datuma!")
    private LocalDate dateTo;
}
