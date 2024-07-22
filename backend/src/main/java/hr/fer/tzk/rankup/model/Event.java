package hr.fer.tzk.rankup.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvent")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nameEvent", nullable = false)
    private String name;

    @NotBlank
    @Temporal(TemporalType.DATE)
    @Column(name = "dateFromEvent", nullable = false)
    private LocalDate dateFrom;

    @NotBlank
    @Temporal(TemporalType.DATE)
    @Column(name = "dateToEvent", nullable = false)
    private LocalDate dateTo;
    
    @Size(max = 80)
    @Column(name = "descriptionEvent")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "idEventType", nullable = false)
    private EventType eventType;

    public Event() { }

    public Event(String name, LocalDate dateFrom, Section section, EventType eventType) {
        this.name = name;
        this.dateFrom = dateFrom;
        this.section = section;
        this.eventType = eventType;
    }

    public Event(String name, LocalDate dateFrom, String description, Section section, EventType eventType) {
        this.name = name;
        this.dateFrom = dateFrom;
        this.description = description;
        this.section = section;
        this.eventType = eventType;
    }

    public Event(String name, LocalDate dateFrom, LocalDate dateTo, String description, Section section, EventType eventType) {
        if (dateFrom.isAfter(dateTo)) {
            throw new IllegalArgumentException("Date from must be before date to");
        }
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.description = description;
        this.section = section;
        this.eventType = eventType;
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

    public @NotBlank LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(@NotBlank LocalDate dateFrom) {
        if (dateFrom.isAfter(dateTo)) {
            throw new IllegalArgumentException("Date from must be before date to");
        }
        this.dateFrom = dateFrom;
    }

    public @NotBlank LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(@NotBlank LocalDate dateTo) {
        if (dateFrom.isAfter(dateTo)) {
            throw new IllegalArgumentException("Date from must be before date to");
        }
        this.dateTo = dateTo;
    }

    public @Size(max = 80) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 80) String description) {
        this.description = description;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
