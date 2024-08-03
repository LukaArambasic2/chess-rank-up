package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Verification", uniqueConstraints = {@UniqueConstraint(columnNames = "url")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVerification")
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "expirationTime", nullable = false)
    private LocalDateTime expirationTime;

    @ManyToOne
    @JoinColumn(name = "idMember", nullable = false)
    private Member member;
}
