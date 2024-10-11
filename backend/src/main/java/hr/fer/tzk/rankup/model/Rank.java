package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;

@Entity
@Table(name = "MyRank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRank")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nameRank", nullable = false)
    private String name;

    @Size(max = 80)
    @Column(name = "image")
    private String image;

    @Column(name = "pointsModifier", nullable = false)
    @ColumnDefault(value = "0")
    private Integer pointsModifier = 0;

    @Column(name = "pointsRequired")
    private Integer pointsRequired;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;
}
