package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "mymember", uniqueConstraints = {@UniqueConstraint(columnNames = "jmbag"), @UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmember")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, name="firstname")
    private String firstName;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, name = "lastname")
    private String lastName;

    @Column(name = "jmbag", nullable = false, unique = true)
    private String jmbag;

    @Column(name = "email", unique = true)
    private String email;

    @Size(max = 255)
    @Column(name = "passwordhash")
    private String passwordHash;

    @Column(name = "salt")
    private String salt;

    @NotNull
    @Column(name = "isverified")
    @ColumnDefault(value = "FALSE")
    private boolean verified = false;
}
