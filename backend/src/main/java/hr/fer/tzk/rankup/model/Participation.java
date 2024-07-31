package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "Participation")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipation;

    @NotBlank
    private int addPoints;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idMember", nullable = false)
    private Member idMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEvent", nullable = false)
    private Event idEvent;

    public Participation() {}

    public Participation(Long idParticipation, int addPoints, Member idMember, Event idEvent) {
        this.idParticipation = idParticipation;
        this.addPoints = addPoints;
        this.idMember = idMember;
        this.idEvent = idEvent;
    }

    public Long getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(Long idParticipation) {
        this.idParticipation = idParticipation;
    }

    public @NotBlank int getAddPoints() {
        return addPoints;
    }

    public void setAddPoints(@NotBlank int addPoints) {
        this.addPoints = addPoints;
    }

    public Member getIdMember() {
        return idMember;
    }

    public void setIdMember(Member idMember) {
        this.idMember = idMember;
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "idParticipation=" + idParticipation +
                ", addPoints=" + addPoints +
                ", idMember=" + idMember +
                ", idEvent=" + idEvent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participation that = (Participation) o;
        return Objects.equals(idMember, that.idMember) && Objects.equals(idEvent, that.idEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMember, idEvent);
    }
}
