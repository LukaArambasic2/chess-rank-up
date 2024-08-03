package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.SectionMemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Comparator;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@ActiveProfiles("test")
public class SectionMemberControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllSectionMembers() {
        // Chess section
        ResponseEntity<List<SectionMemberDto>> chessResponse = restTemplate.exchange(
                "/api/sections/1/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SectionMemberDto>>() {});
        assertNotNull(chessResponse.getBody());

        List<SectionMemberDto> chessMembers = chessResponse.getBody();
        assertThat(chessMembers.size()).isEqualTo(7);

        // Biking section
        ResponseEntity<List<SectionMemberDto>> bikingResponse = restTemplate.exchange(
                "/api/sections/2/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SectionMemberDto>>() {});
        assertNotNull(bikingResponse.getBody());

        List<SectionMemberDto> bikingMembers = bikingResponse.getBody();
        assertThat(bikingMembers.size()).isEqualTo(3);

        // Section that doesn't exist
        ResponseEntity<List<SectionMemberDto>> nonExistentSectionResponse = restTemplate.exchange(
                "/api/sections/0/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SectionMemberDto>>() {});
        assertNotNull(nonExistentSectionResponse.getBody());
        List<SectionMemberDto> nonExistentSectionMembers = nonExistentSectionResponse.getBody();
        assertThat(nonExistentSectionMembers.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnMemberOfSection() {
        // One chess member
        ResponseEntity<SectionMemberDto> chessResponse = restTemplate.getForEntity("/api/sections/1/members/1", SectionMemberDto.class);
        assertThat(chessResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        SectionMemberDto chessMember = chessResponse.getBody();
        assertNotNull(chessMember);

        SectionMemberDto expectedChessMember = new SectionMemberDto("Hrvoje", "Horvat", "0006040945", null, true, 4, "Pijun");
        assertThat(chessMember).isEqualTo(expectedChessMember);

        // One biking member
        ResponseEntity<SectionMemberDto> bikingResponse = restTemplate.getForEntity("/api/sections/2/members/5", SectionMemberDto.class);
        assertThat(bikingResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        SectionMemberDto bikingMember = bikingResponse.getBody();
        assertNotNull(bikingMember);

        SectionMemberDto expectedBikingMember = new SectionMemberDto("Jura", "Juric", "0036540976", "jj53890@pmf.hr", true, 0, "Pijun");
        assertThat(bikingMember).isEqualTo(expectedBikingMember);
    }

    @Test
    void shouldNotReturnMemberOfSection() {
        // Non-existent chess member
        ResponseEntity<SectionMemberDto> chessResponse = restTemplate.getForEntity("/api/sections/1/members/99", SectionMemberDto.class);
        assertThat(chessResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        // Non-existent biking member
        ResponseEntity<SectionMemberDto> bikingResponse = restTemplate.getForEntity("/api/sections/2/members/99", SectionMemberDto.class);
        assertThat(bikingResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCreateMemberOfSection() {
        // TODO: Add this
    }

    @Test
    void shouldNotCreateMemberOfSection() {
        // TODO: Add this
    }

}
