package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionController {
    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping
    public ResponseEntity<List<Section>> getAllSections() {
        List<Section> sections = sectionService.getAllSections();
        return ResponseEntity.ok(sections);
    }
}
