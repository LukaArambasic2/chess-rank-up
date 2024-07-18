package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "Section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSection;

    @NotBlank
    @Size(max = 30)
    private String name;

    @Size(max = 80)
    private String description;

    @NotBlank
    @Size(max = 80)
    private String logo;

    public Section() {}

    public Section(Long idSection, String name, String description, String logo) {
        this.idSection = idSection;
        this.name = name;
        this.description = description;
        this.logo = logo;
    }

    public Long getIdSection() {
        return idSection;
    }

    public void setIdSection(Long idSection) {
        this.idSection = idSection;
    }

    public @NotBlank @Size(max = 30) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 30) String name) {
        this.name = name;
    }

    public @Size(max = 80) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 80) String description) {
        this.description = description;
    }

    public @NotBlank @Size(max = 80) String getLogo() {
        return logo;
    }

    public void setLogo(@NotBlank @Size(max = 80) String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Section{" +
                "idSection=" + idSection +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(name, section.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
