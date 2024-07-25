package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.model.Section;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.annotation.DirtiesContext.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@ActiveProfiles("test")
public class SectionControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllSections() {
        ResponseEntity<List<Section>> response = restTemplate.exchange(
                "/sections",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Section>>() {});

        Section section = new Section(1L);
        section.setName("Chess");
        section.setDescription("We are FER''s Chess section");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Section> sections = response.getBody();
        assertNotNull(sections);
        assertThat(sections.size()).isEqualTo(2);
        assertThat(sections.get(0)).isEqualTo(section);
    }

    @Test
    void shouldReturnSectionChess() {
        ResponseEntity<Section> response = restTemplate.getForEntity("/sections/1", Section.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Section section = response.getBody();
        assertNotNull(section);
        assertThat(section.getName()).isEqualTo("Chess");
        assertThat(section.getDescription()).isEqualTo("We are FER's Chess section");
    }

    @Test
    void shouldReturnNotFoundSection() {
        ResponseEntity<Section> response = restTemplate.getForEntity("/sections/100", Section.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Section section = response.getBody();
        assertNull(section);
    }
}
