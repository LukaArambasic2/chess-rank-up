package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EventType")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEventType")
    private Long id;

    @Column(name = "nameEventType", nullable = false)
    private String name;

    @Column(name = "defaultPoints")
    private int defaultPoints;
}
