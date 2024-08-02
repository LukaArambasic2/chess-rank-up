package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(name = "nameSemester", nullable = false)
    private String name;

    @Column(name = "dateFromSemester", nullable = false)
    private LocalDate dateFrom;

    @Column(name = "dateToSemester", nullable = false)
    private LocalDate dateTo;
}
