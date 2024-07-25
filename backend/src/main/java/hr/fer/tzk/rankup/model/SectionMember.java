package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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
    private Boolean isActive = true;

    @NotNull
    @Column(name = "pointsAll", nullable = false)
    private Integer pointsAll;

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
    public SectionMember(Boolean isActive, Integer pointsAll, Member member, Section section, Rank rank) {
        this.isActive = isActive;
        this.pointsAll = pointsAll;
        this.member = member;
        this.section = section;
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getPointsAll() {
        return pointsAll;
    }

    public void setPointsAll(Integer pointsAll) {
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
                ", isActive=" + isActive +
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