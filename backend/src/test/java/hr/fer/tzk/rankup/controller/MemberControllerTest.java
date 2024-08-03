package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.BasicMemberDto;
import hr.fer.tzk.rankup.dto.DetailedMemberDto;
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
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@ActiveProfiles("test")
public class MemberControllerTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllMembers() {
        ResponseEntity<List<BasicMemberDto>> response = restTemplate.exchange(
                "/api/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BasicMemberDto>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());
        assertThat(response.getBody().size()).isEqualTo(10);
    }

    @Test
    void shouldReturnMemberWithoutEmail() {
        ResponseEntity<DetailedMemberDto> response = restTemplate.getForEntity("/api/members/1", DetailedMemberDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());

        DetailedMemberDto member = new DetailedMemberDto(1L, "Hrvoje", "Horvat", "0006040945", null);
        assertThat(response.getBody()).isEqualTo(member);
    }

    @Test
    void shouldReturnMemberWithEmail() {
        ResponseEntity<DetailedMemberDto> response = restTemplate.getForEntity("/api/members/4", DetailedMemberDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(response.getBody());

        DetailedMemberDto member = new DetailedMemberDto(4L, "Petar", "Petrovic", "0036539669", "pp53838@fer.hr");
        assertThat(response.getBody()).isEqualTo(member);
    }

    @Test
    void shouldNotCreateMember() {
        BasicMemberDto memberWithDuplicateJmbag = new BasicMemberDto("John", "Doe", "0006040945");
        ResponseEntity<String> response1 = restTemplate.postForEntity("/api/members", memberWithDuplicateJmbag, String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response1.getBody()).isEqualTo("JMBAG already in use");

        BasicMemberDto memberMissingFirstName = new BasicMemberDto("", "Doe", "1234567890");
        ResponseEntity<String> response2 = restTemplate.postForEntity("/api/members", memberMissingFirstName, String.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response2.getBody()).isEqualTo("Missing first name");

        BasicMemberDto memberMissingLastName = new BasicMemberDto("John", "", "1234567890");
        ResponseEntity<String> response3 = restTemplate.postForEntity("/api/members", memberMissingLastName, String.class);
        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response3.getBody()).isEqualTo("Missing last name");

        BasicMemberDto memberFirstNameTooLong = new BasicMemberDto("JohnJohnJohnJohnJohnJohnJohnJohnJohnJohn", "Doe", "1234567890");
        ResponseEntity<String> response4 = restTemplate.postForEntity("/api/members", memberFirstNameTooLong, String.class);
        assertThat(response4.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response4.getBody()).isEqualTo("First name too long");

        BasicMemberDto memberLastNameTooLong = new BasicMemberDto("John", "DoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoe", "1234567890");
        ResponseEntity<String> response5 = restTemplate.postForEntity("/api/members", memberLastNameTooLong, String.class);
        assertThat(response5.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response5.getBody()).isEqualTo("Last name too long");

        BasicMemberDto memberMissingJmbag = new BasicMemberDto("John", "Doe", "");
        ResponseEntity<String> response6 = restTemplate.postForEntity("/api/members", memberMissingJmbag, String.class);
        assertThat(response6.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response6.getBody()).isEqualTo("Missing JMBAG");

        BasicMemberDto memberInvalidJmbag = new BasicMemberDto("John", "Doe", "123");
        ResponseEntity<String> response7 = restTemplate.postForEntity("/api/members", memberInvalidJmbag, String.class);
        assertThat(response7.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response7.getBody()).isEqualTo("Invalid JMBAG");
    }

    @Test
    void shouldCreateMember() {
        // TODO: Add this
    }

}
