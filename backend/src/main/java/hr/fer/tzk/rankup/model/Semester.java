package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Semester")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSemester")
    private Long id;

    @Size(max = 30)
    @Column(name = "nameSemester", nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "dateFromSemester", nullable = false)
    private LocalDate dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "dateToSemester", nullable = false)
    private LocalDate dateTo;

}
