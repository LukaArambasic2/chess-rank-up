package hr.fer.tzk.rankup.dto;

public class MemberNotRegisteredDto {
    private String firstName;
    private String lastName;
    private String jmbag;

    public MemberNotRegisteredDto() { }

    public MemberNotRegisteredDto(String firstName, String lastName, String jmbag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
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
}
