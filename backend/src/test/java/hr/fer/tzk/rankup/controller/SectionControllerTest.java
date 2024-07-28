package hr.fer.tzk.rankup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.fer.tzk.rankup.dto.SectionDto;
import hr.fer.tzk.rankup.model.Section;
import jakarta.persistence.SqlResultSetMapping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@ActiveProfiles("test")
public class SectionControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllSections() {
        ResponseEntity<List<SectionDto>> response = restTemplate.exchange(
                "/sections",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SectionDto>>() {});

        SectionDto section = new SectionDto(1L, "Chess");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<SectionDto> sections = response.getBody();
        assertNotNull(sections);
        assertThat(sections.size()).isEqualTo(2);
        assertThat(sections.get(0)).isEqualTo(section);
    }

    @Test
    void shouldReturnSectionsOneByOne() {
        ResponseEntity<SectionDto> response1 = restTemplate.getForEntity("/sections/1", SectionDto.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

        SectionDto section1 = response1.getBody();
        assertNotNull(section1);
        assertThat(section1.getId()).isEqualTo(1L);
        assertThat(section1.getName()).isEqualTo("Chess");

        ResponseEntity<SectionDto> response2 = restTemplate.getForEntity("/sections/2", SectionDto.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);

        SectionDto section2 = response2.getBody();
        assertNotNull(section2);
        assertThat(section2.getId()).isEqualTo(2L);
        assertThat(section2.getName()).isEqualTo("Biking");
    }

    @Test
    void shouldNotFindSection() {
        ResponseEntity<SectionDto> response = restTemplate.getForEntity("/sections/100", SectionDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        SectionDto section = response.getBody();
        assertNull(section);
    }

    @Test
    @DirtiesContext
    void shouldCreateSection() {
        SectionDto newSection = new SectionDto("Powerlifting");
        ResponseEntity<SectionDto> response = restTemplate.postForEntity("/sections", newSection, SectionDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        SectionDto createdSection = response.getBody();
        assertNotNull(createdSection);

        URI locationUri = response.getHeaders().getLocation();
        assertThat(locationUri).isNotNull();

        String expectedPath = "/sections/" + createdSection.getId();
        String actualPath = locationUri.getPath();

        assertThat(actualPath).isEqualTo(expectedPath);
        assertThat(createdSection.getName()).isEqualTo("Powerlifting");
    }

    @Test
//    @DirtiesContext
    void shouldNotCreateSection() {
        SectionDto newSection = new SectionDto("Chess");
        ResponseEntity<String> response = restTemplate.postForEntity("/sections", newSection, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    @DirtiesContext
    void shouldDeleteSection() {
        ResponseEntity<Void> response = restTemplate.exchange("/sections/1", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
//    @DirtiesContext
    void shouldNotDeleteSection() {
        ResponseEntity<Void> response = restTemplate.exchange("/sections/0", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    void shouldUpdateSection() {
        SectionDto newSection = new SectionDto("Powerlifting");
        ResponseEntity<SectionDto> response = restTemplate.exchange("/sections/1", HttpMethod.PUT, new HttpEntity<>(newSection), SectionDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        SectionDto section = response.getBody();
        assertNotNull(section);
        assertThat(section.getId()).isEqualTo(1L);
        assertThat(section.getName()).isEqualTo("Powerlifting");
    }

    @Test
//    @DirtiesContext
    void shouldNotUpdateSection() {
        SectionDto newSection = new SectionDto("Powerlifting");
        ResponseEntity<SectionDto> response = restTemplate.exchange("/sections/0", HttpMethod.PUT, new HttpEntity<>(newSection), SectionDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertNull(response.getBody());
    }

//    @Test
//    @DirtiesContext
//    void shouldPatchSection() {
//        Map<String, Object> updates = Map.of(
//                "name", "Updated Name",
//                "description", "Updated Description",
//                "logo", "Updated Logo"
//        );
//
//        ResponseEntity<SectionDto> response = restTemplate.exchange(
//                "/sections/1",
//                HttpMethod.PATCH,
//                new HttpEntity<>(updates),
//                SectionDto.class
//        );
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        SectionDto section = response.getBody();
//        assertNotNull(section);
//        assertThat(section.getId()).isEqualTo(1L);
//        assertThat(section.getName()).isEqualTo("Updated Name");
//        assertThat(section.getDescription()).isEqualTo("Updated Description");
//        assertThat(section.getLogo()).isEqualTo("Updated Logo");
//
//    }

//    @Test
//    void shouldNotPatch() {
//
//    }

}
