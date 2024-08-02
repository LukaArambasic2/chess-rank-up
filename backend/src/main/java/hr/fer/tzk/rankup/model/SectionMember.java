package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "SectionMember", uniqueConstraints = {@UniqueConstraint(columnNames = {"idMember", "idSection"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSectionMember")
    private Long id;

    @Column(name = "isActive", nullable = false)
    @ColumnDefault(value = "TRUE")
    private boolean active = true;

    @Column(name = "pointsAll", nullable = false)
    @ColumnDefault(value = "0")
    private int pointsAll = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idMember", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idRank", nullable = false)
    private Rank rank;
}
