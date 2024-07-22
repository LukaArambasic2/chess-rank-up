package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "EventType")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEventType")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nameEventType", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "defaultPoints")
    @Min(0)
    private int defaultPoints;

    public EventType() { }

    public EventType(String name) {
        this.name = name;
        this.defaultPoints = 0;
    }

    public EventType(String name, int defaultPoints) {
        this.name = name;
        if (defaultPoints < 0) {
            throw new IllegalArgumentException("Default points must be non-negative");
        }
        this.defaultPoints = defaultPoints;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank @Size(max = 30) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 30) String name) {
        this.name = name;
    }

    public @Min(0) @NotBlank int getDefaultPoints() {
        return defaultPoints;
    }

    public void setDefaultPoints(@Min(0) @NotBlank int defaultPoints) {
        this.defaultPoints = defaultPoints;
    }
}
