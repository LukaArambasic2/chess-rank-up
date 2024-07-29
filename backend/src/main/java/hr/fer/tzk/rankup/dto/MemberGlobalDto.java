package hr.fer.tzk.rankup.dto;

import java.util.Objects;

public class MemberGlobalDto {

    private String firstName;
    private String lastName;
    private String jmbag;
    private String email;

    public MemberGlobalDto() { }

    public MemberGlobalDto(String firstName, String lastName, String jmbag, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberGlobalDto that = (MemberGlobalDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(jmbag, that.jmbag) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, jmbag, email);
    }

    @Override
    public String toString() {
        return "MemberGlobalDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jmbag='" + jmbag + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
