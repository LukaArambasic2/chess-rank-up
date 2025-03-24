package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification", uniqueConstraints = {@UniqueConstraint(columnNames = "url")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idverification")
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "expirationtime", nullable = false)
    private LocalDateTime expirationTime;

    @ManyToOne
    @JoinColumn(name = "idmember", nullable = false)
    private Member member;
}
