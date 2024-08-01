package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "SectionSemester",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idMember", "idSemester", "idSection"} )
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionSemester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSectionSemester")
    private Long id;

    @Column(name = "threshold", nullable = false)
    private int threshold;

    @Column(name = "points", nullable = false)
    private int points = 0;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idSemester", nullable = false)
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idMember", nullable = false)
    private Member member;

}
