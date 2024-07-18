package hr.fer.tzk.rankup.model;

import java.util.Date;
import java.util.Objects;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent;

    @NotBlank
    @Size(max = 30)
    private String name;

    @Min(0)
    private long defaultPoints;

    @NotBlank
    @Temporal(TemporalType.DATE) //ovo provjerit, mislim da je okej
    private Date date;

    @Size(max = 80)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idSection", nullable = false)
    //ovo mi je chatGPT rekao da stavim, cini mi se da je okej,
    //tip je Section jer vuce type iz Sectiona tek
    private Section idSection;

    public Event() {}

    public Event(Long idEvent, String name, long defaultPoints, Date date, String description, Section idSection) {
        this.idEvent = idEvent;
        this.name = name;
        this.defaultPoints = defaultPoints;
        this.date = date;
        this.description = description;
        this.idSection = idSection;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public @NotBlank @Size(max = 30) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 30) String name) {
        this.name = name;
    }

    @Min(0)
    public long getDefaultPoints() {
        return defaultPoints;
    }

    public void setDefaultPoints(@Min(0) long defaultPoints) {
        this.defaultPoints = defaultPoints;
    }

    public @NotBlank Date getDate() {
        return date;
    }

    public void setDate(@NotBlank Date date) {
        this.date = date;
    }

    public @Size(max = 80) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 80) String description) {
        this.description = description;
    }

    public Section getIdSection() {
        return idSection;
    }

    public void setIdSection(Section idSection) {
        this.idSection = idSection;
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", name='" + name + '\'' +
                ", defaultPoints=" + defaultPoints +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", idSection=" + idSection +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) && Objects.equals(date, event.date) && Objects.equals(idSection, event.idSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, idSection);
    }
}

