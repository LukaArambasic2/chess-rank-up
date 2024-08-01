package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.SectionMemberGetDto;

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
        ResponseEntity<List<SectionMemberGetDto>> chessResponse = restTemplate.exchange(
                "/sections/1/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SectionMemberGetDto>>() {});
        assertNotNull(chessResponse.getBody());

        List<SectionMemberGetDto> chessMembers = chessResponse.getBody();
        assertThat(chessMembers.size()).isEqualTo(3);

        chessMembers = chessMembers.stream()
                .sorted(Comparator.comparing(SectionMemberGetDto::getId))
                .toList();

        SectionMemberGetDto chessMember1 = chessMembers.get(0);
        SectionMemberGetDto chessMember2 = chessMembers.get(1);
        SectionMemberGetDto chessMember3 = chessMembers.get(2);
        assertThat(chessMember1.getId()).isEqualTo(1L);
        assertThat(chessMember2.getId()).isEqualTo(3L);
        assertThat(chessMember3.getId()).isEqualTo(4L);

        // Biking section
        ResponseEntity<List<SectionMemberGetDto>> bikingResponse = restTemplate.exchange(
                "/sections/2/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SectionMemberGetDto>>() {});
        assertNotNull(bikingResponse.getBody());

        List<SectionMemberGetDto> bikingMembers = bikingResponse.getBody();
        assertThat(bikingMembers.size()).isEqualTo(2);
        bikingMembers = bikingMembers.stream()
                .sorted(Comparator.comparing(SectionMemberGetDto::getId))
                .toList();

        SectionMemberGetDto bikingMember1 = bikingMembers.get(0);
        SectionMemberGetDto bikingMember2 = bikingMembers.get(1);
        assertThat(bikingMember1.getId()).isEqualTo(2L);
        assertThat(bikingMember2.getId()).isEqualTo(5L);

        // Section that doesn't exist
        ResponseEntity<List<SectionMemberGetDto>> nonExistentSectionResponse = restTemplate.exchange(
                "/sections/0/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SectionMemberGetDto>>() {});
        assertNotNull(nonExistentSectionResponse.getBody());
        List<SectionMemberGetDto> nonExistentSectionMembers = nonExistentSectionResponse.getBody();
        assertThat(nonExistentSectionMembers.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnMemberOfSection() {
        // One chess member
        ResponseEntity<SectionMemberGetDto> chessResponse = restTemplate.getForEntity("/sections/1/members/1", SectionMemberGetDto.class);
        assertThat(chessResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        SectionMemberGetDto chessMember = chessResponse.getBody();
        assertNotNull(chessMember);

        SectionMemberGetDto expectedChessMember = new SectionMemberGetDto(1L, "Hrvoje", "Horvat", "0006040945", null, true, 0, "Pijun");
        assertThat(chessMember).isEqualTo(expectedChessMember);

        // One biking member
        ResponseEntity<SectionMemberGetDto> bikingResponse = restTemplate.getForEntity("/sections/2/members/5", SectionMemberGetDto.class);
        assertThat(bikingResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        SectionMemberGetDto bikingMember = bikingResponse.getBody();
        assertNotNull(bikingMember);

        SectionMemberGetDto expectedBikingMember = new SectionMemberGetDto(5L, "Jura", "Juric", "0036540976", "jj53890@pmf.hr", true, 0, "Pijun");
        assertThat(bikingMember).isEqualTo(expectedBikingMember);
    }

    @Test
    void shouldNotReturnMemberOfSection() {
        // Non-existent chess member
        ResponseEntity<SectionMemberGetDto> chessResponse = restTemplate.getForEntity("/sections/1/members/99", SectionMemberGetDto.class);
        assertThat(chessResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        // Non-existent biking member
        ResponseEntity<SectionMemberGetDto> bikingResponse = restTemplate.getForEntity("/sections/2/members/99", SectionMemberGetDto.class);
        assertThat(bikingResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldCreateMemberOfSection() {

    }

    @Test
    void shouldNotCreateMemberOfSection() {

    }

}
