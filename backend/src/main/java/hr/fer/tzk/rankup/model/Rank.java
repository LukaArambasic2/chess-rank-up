package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;

@Entity
@Table(name = "MyRank")
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
    private int pointsModifier = 0;

    @Column(name = "pointsRequired")
    private int pointsRequired;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;

    public Rank() { }

    public Rank(String name, String image, int pointsModifier, int pointsRequired, Section section) {
        this.name = name;
        this.image = image;
        this.pointsModifier = pointsModifier;
        this.pointsRequired = pointsRequired;
        this.section = section;
    }

    public Rank(Long id, String name, String image, int pointsModifier, int pointsRequired, Section section) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.pointsModifier = pointsModifier;
        this.pointsRequired = pointsRequired;
        this.section = section;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPointsModifier() {
        return pointsModifier;
    }

    public void setPointsModifier(int pointsModifier) {
        this.pointsModifier = pointsModifier;
    }

    public int getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(int pointsRequired) {
        this.pointsRequired = pointsRequired;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", pointsModifier=" + pointsModifier +
                ", pointsRequired=" + pointsRequired +
                ", section=" + section +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return Objects.equals(name, rank.name) && Objects.equals(section, rank.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, section);
    }
}
