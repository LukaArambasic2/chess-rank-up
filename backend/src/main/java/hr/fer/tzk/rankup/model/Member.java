package hr.fer.tzk.rankup.model;

import hr.fer.tzk.rankup.validation.ValidEmail;
import hr.fer.tzk.rankup.validation.ValidJmbag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMember")
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    private String lastName;

    @ValidJmbag
    @NotBlank
    @Size(min = 10, max = 10)
    private String jmbag;

    @ValidEmail
    @Size(max = 50)
    private String email;

    @Size(max = 255)
    private String passwordHash;

    @Size(max = 32)
    private String salt;

    // Empty constructor
    public Member() {}

    // Use case: Member is added in a database by the section leader
    public Member(String firstName, String lastName, String jmbag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
    }

    // General full args constructor
    // Use case: Member is added after registration
    public Member(String firstName, String lastName, String jmbag, String email, String passwordHash, String salt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 30) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank @Size(max = 30) String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank @Size(max = 30) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank @Size(max = 30) String lastName) {
        this.lastName = lastName;
    }

    public @ValidJmbag @NotBlank @Size(min = 10, max = 10) String getJmbag() {
        return jmbag;
    }

    public void setJmbag(@ValidJmbag @NotBlank @Size(min = 10, max = 10) String jmbag) {
        this.jmbag = jmbag;
    }

    public @ValidEmail @Size(max = 50) String getEmail() {
        return email;
    }

    public void setEmail(@ValidEmail @Size(max = 50) String email) {
        this.email = email;
    }

    public @Size(max = 255) String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(@Size(max = 255) String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public @Size(max = 32) String getSalt() {
        return salt;
    }

    public void setSalt(@Size(max = 32) String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jmbag='" + jmbag + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(firstName, member.firstName) && Objects.equals(lastName, member.lastName) && Objects.equals(jmbag, member.jmbag) && Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, jmbag, email);
    }
}
