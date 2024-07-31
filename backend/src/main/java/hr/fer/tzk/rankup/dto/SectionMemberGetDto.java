package hr.fer.tzk.rankup.dto;

import java.util.Objects;

public class SectionMemberGetDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String jmbag;
    private String email;
    private boolean active;
    private int pointsAll;
    private String rank;

    public SectionMemberGetDto() { }

    public SectionMemberGetDto(String firstName, String lastName, String jmbag, String email, boolean active, int pointsAll, String rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.email = email;
        this.active = active;
        this.pointsAll = pointsAll;
        this.rank = rank;
    }

    public SectionMemberGetDto(Long id, String firstName, String lastName, String jmbag, String email, boolean active, int pointsAll, String rank) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.email = email;
        this.active = active;
        this.pointsAll = pointsAll;
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPointsAll() {
        return pointsAll;
    }

    public void setPointsAll(int pointsAll) {
        this.pointsAll = pointsAll;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionMemberGetDto that = (SectionMemberGetDto) o;
        return active == that.active && pointsAll == that.pointsAll && Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(jmbag, that.jmbag) && Objects.equals(email, that.email) && Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, jmbag, email, active, pointsAll, rank);
    }

    @Override
    public String toString() {
        return "SectionMemberGetDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jmbag='" + jmbag + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", pointsAll=" + pointsAll +
                ", rank='" + rank + '\'' +
                '}';
    }
}
