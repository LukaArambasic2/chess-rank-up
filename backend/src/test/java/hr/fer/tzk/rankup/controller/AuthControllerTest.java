package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.form.LoginForm;
import hr.fer.tzk.rankup.form.RegisterForm;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.security.Argon2idHasher;
import hr.fer.tzk.rankup.security.PasswordHasher;
import hr.fer.tzk.rankup.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    MemberService memberService;

    @Test
    @DirtiesContext
    void shouldRegisterNewMember() {
        RegisterForm newMember = new RegisterForm("Josko", "Jovanovic", "0036540383", "jj54038@fer.hr", "password1", "password1");
        ResponseEntity<String> response1 = restTemplate.postForEntity("/api/auth/register", newMember, String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        Optional<Member> memberOpt = memberService.findMemberByJmbag("0036540383");
        assertThat(memberOpt.isPresent()).isTrue();

        Member member = memberOpt.get();
        assertNotNull(member);
        assertThat(member.getFirstName()).isEqualTo(newMember.getFirstName());
        assertThat(member.getLastName()).isEqualTo(newMember.getLastName());
        assertThat(member.getJmbag()).isEqualTo(newMember.getJmbag());

        PasswordHasher hasher = new Argon2idHasher();
        String salt = member.getSalt();
        assertThat(hasher.checkPassword(newMember.getPassword(), salt, member.getPasswordHash())).isTrue();
    }

    @Test
    @DirtiesContext
    void shouldNotRegisterNewMember() {
        // Correct registration
        RegisterForm newMember1 = new RegisterForm("Josko", "Jovanovic", "0036540383", "jj54038@fer.hr", "password1", "password1");
        ResponseEntity<String> response1 = restTemplate.postForEntity("/api/auth/register", newMember1, String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Conflicts
        RegisterForm newMember2 = new RegisterForm("Josko", "Jovanovic", "0036540357", "jj54038@fer.hr", "password1", "password1");
        ResponseEntity<String> response2 = restTemplate.postForEntity("/api/auth/register", newMember2, String.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response2.getBody()).isEqualTo("Email already in use");

        RegisterForm newMember3 = new RegisterForm("Ivan", "Ivanovic", "0036540383", "ivan.ivanovic@fer.hr", "password1", "password1");
        ResponseEntity<String> response3 = restTemplate.postForEntity("/api/auth/register", newMember3, String.class);
        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response3.getBody()).isEqualTo("JMBAG already in use");

        // Missing attributes
        RegisterForm newMember4 = new RegisterForm("", "Duric", "0036540357", "duro.duric@fer.hr", "password1", "password1");
        ResponseEntity<String> response4 = restTemplate.postForEntity("/api/auth/register", newMember4, String.class);
        assertThat(response4.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response4.getBody()).isEqualTo("Missing first name");

        RegisterForm newMember5 = new RegisterForm("Duro", "", "0036540357", "duro.duric@fer.hr", "password1", "password1");
        ResponseEntity<String> response5 = restTemplate.postForEntity("/api/auth/register", newMember5, String.class);
        assertThat(response5.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response5.getBody()).isEqualTo("Missing last name");

        RegisterForm newMember6 = new RegisterForm("Duro", "Duric", "", "duro.duric@fer.hr", "password1", "password1");
        ResponseEntity<String> response6 = restTemplate.postForEntity("/api/auth/register", newMember6, String.class);
        assertThat(response6.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response6.getBody()).isEqualTo("Missing JMBAG");

        RegisterForm newMember7 = new RegisterForm("Duro", "Duric", "0036540357", "", "password1", "password1");
        ResponseEntity<String> response7 = restTemplate.postForEntity("/api/auth/register", newMember7, String.class);
        assertThat(response7.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response7.getBody()).isEqualTo("Missing email");

        RegisterForm newMember8 = new RegisterForm("Duro", "Duric", "0036540357", "duro.duric@fer.hr", "", "password1");
        ResponseEntity<String> response8 = restTemplate.postForEntity("/api/auth/register", newMember8, String.class);
        assertThat(response8.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response8.getBody()).isEqualTo("Missing password");

        RegisterForm newMember9 = new RegisterForm("Duro", "Duric", "0036540357", "duro.duric@fer.hr", "password1", "");
        ResponseEntity<String> response9 = restTemplate.postForEntity("/api/auth/register", newMember9, String.class);
        assertThat(response9.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response9.getBody()).isEqualTo("Missing repeat password");

        // Attributes don't satisfy certain logic
        RegisterForm newMember10 = new RegisterForm("Duro", "Duric", "0036540357", "duro duric@fer.hr", "password1", "password1");
        ResponseEntity<String> response10 = restTemplate.postForEntity("/api/auth/register", newMember10, String.class);
        assertThat(response10.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response10.getBody()).isEqualTo("Invalid email");

        RegisterForm newMember11 = new RegisterForm("Duro", "Duric", "0036540358", "duro.duric@fer.hr", "password1", "password1");
        ResponseEntity<String> response11 = restTemplate.postForEntity("/api/auth/register", newMember11, String.class);
        assertThat(response11.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response11.getBody()).isEqualTo("Invalid JMBAG");

        RegisterForm newMember12 = new RegisterForm("Duro", "Duric", "0036540357", "duro.duric@fer.hr", "passwordword 1", "passwordword 1");
        ResponseEntity<String> response12 = restTemplate.postForEntity("/api/auth/register", newMember12, String.class);
        assertThat(response12.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response12.getBody()).isEqualTo("Password can contain only: uppercase and lowercase letters, numbers and special characters '.', '?', '!', '_' and '-'");
    }

    @Test
    void shouldLoginMember() {
        LoginForm member = new LoginForm("jj56789@fer.hr", "password1");
        ResponseEntity<String> response = restTemplate.postForEntity("/api/auth/login", member, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldNotLoginMember() {
        // Missing data
        LoginForm member1 = new LoginForm("   ", "password1");
        ResponseEntity<String> response1 = restTemplate.postForEntity("/api/auth/login", member1, String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        LoginForm member2 = new LoginForm("i123@fer.hr", "        ");
        ResponseEntity<String> response2 = restTemplate.postForEntity("/api/auth/login", member2, String.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        LoginForm member3 = new LoginForm("i123@fer.hr", "password1");
        ResponseEntity<String> response3 = restTemplate.postForEntity("/api/auth/login", member3, String.class);
        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // Member doesn't exist
        LoginForm member4 = new LoginForm("i123@fer.hr", "password1");
        ResponseEntity<String> response4 = restTemplate.postForEntity("/api/auth/login", member4, String.class);
        assertThat(response4.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        LoginForm member5 = new LoginForm("jj56789@fer.hr", "password");
        ResponseEntity<String> response5 = restTemplate.postForEntity("/api/auth/login", member5, String.class);
        assertThat(response5.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        LoginForm member6 = new LoginForm("pp53838@fer.hr", "password2");
        ResponseEntity<String> response6 = restTemplate.postForEntity("/api/auth/login", member6, String.class);
        assertThat(response6.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        LoginForm member7 = new LoginForm("pp53838@fer.hr", "password2 ");
        ResponseEntity<String> response7 = restTemplate.postForEntity("/api/auth/login", member7, String.class);
        assertThat(response7.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
