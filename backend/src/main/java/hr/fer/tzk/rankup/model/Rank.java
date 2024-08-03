package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "nameRank", nullable = false)
    private String name;

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
