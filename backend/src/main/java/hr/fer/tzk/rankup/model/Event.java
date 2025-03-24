package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "myevent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idevent")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nameevent", nullable = false)
    private String name;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "dateevent", nullable = false)
    private LocalDate date;
    
    @Size(max = 80)
    @Column(name = "descriptionevent")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idsection", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "ideventtype", nullable = false)
    private EventType eventType;
}
