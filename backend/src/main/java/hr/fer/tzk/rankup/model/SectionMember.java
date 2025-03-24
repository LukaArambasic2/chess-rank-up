package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "sectionmember", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idmember", "idsection"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsectionmember")
    private Long id;

    @Column(name = "isactive", nullable = false)
    @ColumnDefault(value = "TRUE")
    private boolean active = true;

    @Column(name = "pointsall", nullable = false)
    @ColumnDefault(value = "0")
    private int pointsAll = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idmember", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idsection", nullable = false)
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idrank", nullable = false)
    private Rank rank;
}
