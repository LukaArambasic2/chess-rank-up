package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "Participation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipation;

    @NotBlank
    private int addPoints;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idMember", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEvent", nullable = false)
    private Event event;

}
