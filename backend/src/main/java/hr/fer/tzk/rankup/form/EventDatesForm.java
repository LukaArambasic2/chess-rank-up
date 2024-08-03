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

    @NotNull(message = "Start date is missing")
    private LocalDate dateFrom;

    @NotNull(message = "End date is missing ")
    private LocalDate dateTo;
}
