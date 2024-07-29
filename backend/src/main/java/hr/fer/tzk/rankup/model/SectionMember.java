package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;

@Entity
@Table(name = "SectionMember", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idMember", "idSection"})
})
public class SectionMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSectionMember")
    private Long id;

    @NotNull
    @Column(name = "isActive", nullable = false)
    @ColumnDefault(value = "TRUE")
    private boolean active = true;

    @NotNull
    @Column(name = "pointsAll", nullable = false)
    @ColumnDefault(value = "0")
    private int pointsAll = 0;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idMember", nullable = false)
    private Member member;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idRank", nullable = false)
    private Rank rank;

    // Empty constructor
    public SectionMember() { }

    // Full args constructor
    public SectionMember(boolean active, int pointsAll, Member member, Section section, Rank rank) {
        this.active = active;
        this.pointsAll = pointsAll;
        this.member = member;
        this.section = section;
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public boolean isActive() {
        return active;
    }

    public void setActive(@NotNull boolean active) {
        this.active = active;
    }

    public int getPointsAll() {
        return pointsAll;
    }

    public void setPointsAll(int pointsAll) {
        this.pointsAll = pointsAll;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "SectionMember{" +
                "id=" + id +
                ", active=" + active +
                ", pointsAll=" + pointsAll +
                ", member=" + member +
                ", section=" + section +
                ", rank=" + rank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionMember that = (SectionMember) o;
        return Objects.equals(member, that.member) && Objects.equals(section, that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, section);
    }
}
