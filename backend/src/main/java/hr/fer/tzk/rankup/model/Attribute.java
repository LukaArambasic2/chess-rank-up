package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "MyAttribute")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAttribute")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nameAttribute", nullable = false)
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column(name = "datType", nullable = false)
    private String dataType;

    public Attribute() { }

    public Attribute(Long id, String name, String dataType) {
        this.id = id;
        this.name = name;
        this.dataType = dataType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 30) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 30) String name) {
        this.name = name;
    }

    public @NotBlank @Size(max = 20) String getDataType() {
        return dataType;
    }

    public void setDataType(@NotBlank @Size(max = 20) String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
