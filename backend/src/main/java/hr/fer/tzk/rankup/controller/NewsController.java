package hr.fer.tzk.rankup.controller;


import hr.fer.tzk.rankup.dto.NewsDto;
import hr.fer.tzk.rankup.form.NewsForm;
import hr.fer.tzk.rankup.model.News;
import hr.fer.tzk.rankup.service.NewsService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<NewsDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(newsService.findAll());
    }

    @GetMapping("/author/{idAuthor}")
    public ResponseEntity<List<NewsDto>> findAllByAuthor(@PathVariable Long idAuthor) {
        return ResponseEntity.status(HttpStatus.OK).body(newsService.findAllByAuthor(idAuthor));
    }

    @GetMapping("/section/{idSection}")
    public ResponseEntity<List<NewsDto>> findAllBySection(@PathVariable Long idSection) {
        return ResponseEntity.status(HttpStatus.OK).body(newsService.findAllBySection(idSection));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(newsService.findById(id));
    }

    @PostMapping
    public ResponseEntity<NewsDto> createNews(@RequestBody @Valid NewsForm newsForm) throws BadRequestException {
        News news = newsService.createNews(newsForm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(news.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }
}
