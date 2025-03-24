package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "myrank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrank")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "namerank", nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "pointsmodifier", nullable = false)
    @ColumnDefault(value = "0")
    private Integer pointsModifier = 0;

    @Column(name = "pointsrequired")
    private Integer pointsRequired;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idsection", nullable = false)
    private Section section;
}
