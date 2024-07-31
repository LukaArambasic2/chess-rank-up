package hr.fer.tzk.rankup.dto;

public class MemberRegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
    private String jmbag;

    public MemberRegisterDto() {}

    public MemberRegisterDto(String firstName, String lastName, String jmbag, String email, String password, String repeatPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    @Override
    public String toString() {
        return "MemberRegister{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", jmbag='" + jmbag + '\'' +
                '}';
    }
}
