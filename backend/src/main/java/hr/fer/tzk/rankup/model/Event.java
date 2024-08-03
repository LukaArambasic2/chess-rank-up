package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "MyEvent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvent")
    private Long id;

    @Column(name = "nameEvent", nullable = false)
    private String name;

    @Column(name = "dateEvent", nullable = false)
    private LocalDate date;

    @Column(name = "descriptionEvent")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "idEventType", nullable = false)
    private EventType eventType;
}
