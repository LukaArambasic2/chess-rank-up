package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "Verification", uniqueConstraints = { @UniqueConstraint(columnNames = "url") })
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVerification")
    private Long id;

    @Column(name = "url", nullable = false)
    @Size(max = 80)
    private String url;

    @Column(name = "expirationTime", nullable = false)
    private LocalDateTime expirationTime;

    @ManyToOne
    @JoinColumn(name = "idMember", nullable = false)
    private Member member;

    public Verification() {}

    public Verification(String url, LocalDateTime expirationTime, Member member) {
        this.url = url;
        this.expirationTime = expirationTime;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
