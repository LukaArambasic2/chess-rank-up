package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.MemberGlobalDto;
import hr.fer.tzk.rankup.dto.MemberNotRegisteredDto;
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
        ResponseEntity<List<MemberGlobalDto>> response = restTemplate.exchange(
                "/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MemberGlobalDto>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody().size()).isEqualTo(5);
    }

    @Test
    void shouldReturnMemberWithoutEmail() {
        ResponseEntity<MemberGlobalDto> response = restTemplate.getForEntity("/members/1", MemberGlobalDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());

        MemberGlobalDto member = new MemberGlobalDto("Hrvoje", "Horvat", "0006040945", null);
        assertThat(response.getBody()).isEqualTo(member);
    }

    @Test
    void shouldReturnMemberWithEmail() {
        ResponseEntity<MemberGlobalDto> response = restTemplate.getForEntity("/members/4", MemberGlobalDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());

        MemberGlobalDto member = new MemberGlobalDto("Petar", "Petrovic", "0036539669", "pp53838@fer.hr");
        assertThat(response.getBody()).isEqualTo(member);
    }

    @Test
    void shouldNotCreateMember() {
        MemberNotRegisteredDto memberWithDuplicateJmbag = new MemberNotRegisteredDto("John", "Doe", "0006040945");
        ResponseEntity<String> response1 = restTemplate.postForEntity("/members", memberWithDuplicateJmbag, String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response1.getBody()).isEqualTo("JMBAG already in use");

        MemberNotRegisteredDto memberMissingFirstName = new MemberNotRegisteredDto("", "Doe", "1234567890");
        ResponseEntity<String> response2 = restTemplate.postForEntity("/members", memberMissingFirstName, String.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response2.getBody()).isEqualTo("Missing first name");

        MemberNotRegisteredDto memberMissingLastName = new MemberNotRegisteredDto("John", "", "1234567890");
        ResponseEntity<String> response3 = restTemplate.postForEntity("/members", memberMissingLastName, String.class);
        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response3.getBody()).isEqualTo("Missing last name");

        MemberNotRegisteredDto memberFirstNameTooLong = new MemberNotRegisteredDto("JohnJohnJohnJohnJohnJohnJohnJohnJohnJohn", "Doe", "1234567890");
        ResponseEntity<String> response4 = restTemplate.postForEntity("/members", memberFirstNameTooLong, String.class);
        assertThat(response4.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response4.getBody()).isEqualTo("First name too long");

        MemberNotRegisteredDto memberLastNameTooLong = new MemberNotRegisteredDto("John", "DoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoe", "1234567890");
        ResponseEntity<String> response5 = restTemplate.postForEntity("/members", memberLastNameTooLong, String.class);
        assertThat(response5.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response5.getBody()).isEqualTo("Last name too long");

        MemberNotRegisteredDto memberMissingJmbag = new MemberNotRegisteredDto("John", "Doe", "");
        ResponseEntity<String> response6 = restTemplate.postForEntity("/members", memberMissingJmbag, String.class);
        assertThat(response6.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response6.getBody()).isEqualTo("Missing JMBAG");

        MemberNotRegisteredDto memberInvalidJmbag = new MemberNotRegisteredDto("John", "Doe", "123");
        ResponseEntity<String> response7 = restTemplate.postForEntity("/members", memberInvalidJmbag, String.class);
        assertThat(response7.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response7.getBody()).isEqualTo("Invalid JMBAG");
    }

    @Test
    void shouldCreateMember() {

    }

}
