package hr.fer.tzk.rankup.model;

import hr.fer.tzk.rankup.utils.EmailUtils;
import hr.fer.tzk.rankup.utils.JmbagUtils;
import hr.fer.tzk.rankup.validation.ValidEmail;
import hr.fer.tzk.rankup.validation.ValidJmbag;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "Member", uniqueConstraints = {
        @UniqueConstraint(columnNames = "jmbag"),
        @UniqueConstraint(columnNames = "email")
})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMember")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false)
    private String lastName;

    @ValidJmbag
    @NotBlank
    @Size(min = 10, max = 10)
    @Column(name = "jmbag", nullable = false, unique = true)
    private String jmbag;

    @ValidEmail
    @Size(max = 50)
    @Column(name = "email", unique = true)
    private String email;

    @Size(max = 255)
    @Column(name = "passwordHash")
    private String passwordHash;

    @Size(max = 32)
    @Column(name = "salt")
    private String salt;

    @Size(max = 64)
    @Column(name = "verifyCode")
    private String verificationCode;

    @Column(name = "isVerified")
    private boolean verified = false;

    // Empty constructor
    public Member() {}

    // Use case: Member is added by the section leader into the database
    public Member(String firstName, String lastName, String jmbag) {
        if (!JmbagUtils.validateJmbag(jmbag)) {
            throw new IllegalArgumentException("Invalid JMBAG");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.verified = false;
    }

    // Use case: Member is added by the section leader into the database with email specified
    public Member(String firstName, String lastName, String jmbag, String email) {
        if (!JmbagUtils.validateJmbag(jmbag)) {
            throw new IllegalArgumentException("Invalid JMBAG");
        } else if (!EmailUtils.validateEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        // TODO: Check for two versions of email, eg. ii45678@fer.hr and ivan.ivanovic@fer.hr are both valid
        // but only one should be checked if it is compatible with JMBAG.
        // Or remove feature altogether.
        /*
        else if (email != null && !email.substring(2, 7).equals(jmbag.substring(4, 9))) {
            throw new IllegalArgumentException("Middle part of email and JMBAG don't match");
        }
        */
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.email = email;
        this.verified = false;
    }

    // General full args constructor
    // Use case: Member is added after registration
    public Member(String firstName, String lastName, String jmbag, String email, String passwordHash, String salt) {
        if (!JmbagUtils.validateJmbag(jmbag)) {
            throw new IllegalArgumentException("Invalid JMBAG");
        } else if (!EmailUtils.validateEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        /*
        else if (email != null && !email.substring(2, 7).equals(jmbag.substring(4, 9))) {
            throw new IllegalArgumentException("Middle part of email and JMBAG don't match");
        }
        */
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.verified = false;
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
        if (!JmbagUtils.validateJmbag(jmbag)) {
            throw new IllegalArgumentException("Invalid JMBAG");
        }
        /*
        else if (email != null && email != null && !email.substring(2, 7).equals(jmbag.substring(4, 9))) {
            throw new IllegalArgumentException("Middle part of email and JMBAG don't match");
        }
        */
        this.jmbag = jmbag;
    }

    public @ValidEmail @Size(max = 50) String getEmail() {
        return email;
    }

    public void setEmail(@ValidEmail @Size(max = 50) String email) {
        if (!EmailUtils.validateEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        /*
        else if (email != null && !email.substring(2, 7).equals(jmbag.substring(4, 9))) {
            throw new IllegalArgumentException("Middle part of email and JMBAG don't match");
        }
        */

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

    public @Size(max = 64) String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(@Size(max = 64) String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
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
                ", salt='" + salt + '\'' +
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
