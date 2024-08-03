package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "MyMember", uniqueConstraints = {@UniqueConstraint(columnNames = "jmbag"), @UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMember")
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "jmbag", nullable = false, unique = true)
    private String jmbag;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "salt")
    private String salt;

    @Column(name = "isVerified")
    @ColumnDefault(value = "FALSE")
    private boolean verified = false;
}
