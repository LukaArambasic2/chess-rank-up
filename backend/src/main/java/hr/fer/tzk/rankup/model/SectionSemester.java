package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "SectionSemester",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idMember", "idSemester", "idSection"} )
})
public class SectionSemester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSectionSemester")
    private Long id;

    @NotBlank
    @Column(name = "threshold", nullable = false)
    private int threshold;

    @NotBlank
    @Column(name = "points", nullable = false)
    private int points = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idSemester", nullable = false)
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idMember", nullable = false)
    private Member member;

    public SectionSemester() { }

    public SectionSemester(Member member, Section section, Semester semester, int points, int threshold) {
        this.member = member;
        this.section = section;
        this.semester = semester;
        this.points = points;
        this.threshold = threshold;
    }

    public Long getId() {
        return id;
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

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @NotBlank
    public int getPoints() {
        return points;
    }

    public void setPoints(@NotBlank int points) {
        this.points = points;
    }

    @NotBlank
    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(@NotBlank int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionSemester that = (SectionSemester) o;
        return threshold == that.threshold && points == that.points && Objects.equals(semester, that.semester) && Objects.equals(section, that.section) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threshold, points, semester, section, member);
    }
}
