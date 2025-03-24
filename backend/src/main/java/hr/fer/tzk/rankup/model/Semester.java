package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Column(name = "idsemester")
    private Long id;

    @Size(max = 30)
    @Column(name = "namesemester", nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "datefromsemester", nullable = false)
    private LocalDate dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "datetosemester", nullable = false)
    private LocalDate dateTo;
}
