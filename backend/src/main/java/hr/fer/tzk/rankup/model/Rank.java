package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "Rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRank;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 80)
    private String image;

    @NotBlank
    private Long pointsModifier;

    @NotBlank
    private Long pointsRequired;

    //opet provjerit ManyToOne
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idSection", nullable = false)
    private Section idSection;

    public Rank() {}

    public Rank(Long idRank, String name, String image, Long pointsModifier, Long pointsRequired, Section idSection) {
        this.idRank = idRank;
        this.name = name;
        this.image = image;
        this.pointsModifier = pointsModifier;
        this.pointsRequired = pointsRequired;
        this.idSection = idSection;
    }

    public Long getIdRank() {
        return idRank;
    }

    public void setIdRank(Long idRank) {
        this.idRank = idRank;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank @Size(max = 80) String getImage() {
        return image;
    }

    public void setImage(@NotBlank @Size(max = 80) String image) {
        this.image = image;
    }

    public @NotBlank Long getPointsModifier() {
        return pointsModifier;
    }

    public void setPointsModifier(@NotBlank Long pointsModifier) {
        this.pointsModifier = pointsModifier;
    }

    public @NotBlank Long getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(@NotBlank Long pointsRequired) {
        this.pointsRequired = pointsRequired;
    }

    public Section getIdSection() {
        return idSection;
    }

    public void setIdSection(Section idSection) {
        this.idSection = idSection;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "idRank=" + idRank +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", pointsModifier=" + pointsModifier +
                ", pointsRequired=" + pointsRequired +
                ", idSection=" + idSection +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return Objects.equals(name, rank.name) && Objects.equals(idSection, rank.idSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, idSection);
    }
}
