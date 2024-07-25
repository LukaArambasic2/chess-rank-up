package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.MemberRegisterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldRegisterNewUser() {
        MemberRegisterDto newMember = new MemberRegisterDto("Josko", "Jovanovic", "0036540383", "jj54038@fer.hr", "1234", "1234");
        ResponseEntity<String> response = restTemplate.postForEntity("/auth/register", newMember, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
