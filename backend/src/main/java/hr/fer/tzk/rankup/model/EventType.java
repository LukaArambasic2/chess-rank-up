package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "eventtype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideventtype")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nameeventtype", nullable = false)
    private String name;

    @NotNull
    @Column(name = "defaultpoints")
    @Min(0)
    private int defaultPoints;

    public @NotBlank @Size(max = 30) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 30) String name) {
        this.name = name;
    }

    public @Min(0) @NotNull int getDefaultPoints() {
        return defaultPoints;
    }

    public void setDefaultPoints(@Min(0) @NotNull int defaultPoints) {
        this.defaultPoints = defaultPoints;
    }
}
