package hr.fer.tzk.rankup.model;

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
        assertThat(sections.size()).isEqualTo(1);
        assertThat(sections.get(0)).isEqualTo(section);
    }
}
