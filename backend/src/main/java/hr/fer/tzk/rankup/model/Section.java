package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "MySection", uniqueConstraints = @UniqueConstraint(columnNames = "nameSection"))
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSection")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nameSection", nullable = false)
    private String name;

    @Size(max = 80)
    @Column(name = "descriptionSection")
    private String description;

    @Size(max = 80)
    @Column(name = "logo")
    private String logo;

    public Section() { }

    public Section(Long id) {
        this.id = id;
    }

    public Section(String name) {
        this.name = name;
    }

    public Section(String name, String description, String logo) {
        this.name = name;
        this.description = description;
        this.logo = logo;
    }

    public Section(Long id, String name, String description, String logo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public @Size(max = 80) String getLogo() {
        return logo;
    }

    public void setLogo(@Size(max = 80) String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
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
