package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@ActiveProfiles("test")
public class MemberControllerTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllMembers() {
        ResponseEntity<List<Member>> response = restTemplate.exchange(
                "/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Member>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody().size()).isEqualTo(5);
    }

    @Test
    void shouldReturnMemberWithoutEmail() {
        ResponseEntity<Member> response = restTemplate.getForEntity("/members/1", Member.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());

        Member member = new Member("Hrvoje", "Horvat", "1234567890");
        assertThat(response.getBody()).isEqualTo(member);
    }

    @Test
    void shouldReturnMemberWithEmail() {
        ResponseEntity<Member> response = restTemplate.getForEntity("/members/4", Member.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());

        Member member = new Member("Petar", "Petrovic", "0036538383", "pp53838@fer.hr");
        assertThat(response.getBody()).isEqualTo(member);
    }

    @Test
    void shouldNotCreateMembersBecauseNotUniqueAttrs() {
        Member memberWithDuplicateJmbag = new Member("John", "Doe", "1234567890");
        ResponseEntity<String> response1 = restTemplate.postForEntity("/members", memberWithDuplicateJmbag, String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response1.getBody()).isEqualTo("JMBAG already exists");

        Member memberWithDuplicateEmail = new Member("John", "Doe", "0036540383", "jj53890@pmf.hr");
        ResponseEntity<String> response2 = restTemplate.postForEntity("/members", memberWithDuplicateEmail, String.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response2.getBody()).isEqualTo("Email already exists");
    }

    @Test
    void shouldNotCreateMembersBecauseInvalidAttrs() {
        // Create a JSON string with invalid JMBAG
        String memberWithInvalidJmbagJson = "{\"firstName\":\"John\", \"lastName\":\"Doe\", \"jmbag\":\"0036540383\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(memberWithInvalidJmbagJson, headers);

        ResponseEntity<String> response1 = restTemplate.postForEntity("/members", request, String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
//        assertThat(response1.getBody()).contains("Invalid JMBAG");

        // Create a JSON string with invalid email
        String memberWithInvalidEmailJson = "{\"firstName\":\"John\", \"lastName\":\"Doe\", \"jmbag\":\"0036540383\", \"email\":\"jj\"}";
        HttpEntity<String> request2 = new HttpEntity<>(memberWithInvalidEmailJson, headers);

        ResponseEntity<String> response2 = restTemplate.postForEntity("/members", request2, String.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response2.getBody()).contains("Invalid email");
    }

}
