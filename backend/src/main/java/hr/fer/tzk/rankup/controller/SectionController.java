package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.SectionDto;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.model.SectionMember;
import hr.fer.tzk.rankup.service.SectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        List<Section> sections = sectionService.findAllSections();
        return ResponseEntity.ok(sections);
    }

    @GetMapping("/{idSection}")
    public ResponseEntity<SectionDto> getSectionById(@PathVariable Long idSection) {
        Optional<Section> sectionOpt = sectionService.findSectionById(idSection);
        if (sectionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Section section = sectionOpt.get();
        SectionDto sectionDto = new SectionDto(section.getId(), section.getName());
        return ResponseEntity.ok(sectionDto);
    }

    @PostMapping
    public ResponseEntity<SectionDto> createSection(@RequestBody SectionDto sectionDto) {
        if (sectionDto.getName() == null || sectionDto.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (sectionDto.getName().length() > 30) {
            return ResponseEntity.badRequest().build();
        }

        Section newSection = new Section(sectionDto.getName());
        Optional<Section> storedSectionOpt = sectionService.findSectionByName(newSection.getName());
        if (storedSectionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        try {
            Section createdSection = sectionService.createSection(newSection);
            SectionDto createdSectionDto = new SectionDto(createdSection.getId(), createdSection.getName());
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdSection.getId())
                    .toUri();

            return ResponseEntity.created(location).body(createdSectionDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idSection}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long idSection) {
        try {
            sectionService.deleteSectionById(idSection);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idSection}")
    public ResponseEntity<SectionDto> updateSection(@PathVariable Long idSection, @RequestBody SectionDto sectionDto) {
        Optional<Section> sectionOpt = sectionService.findSectionById(idSection);
        if (sectionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Section updatedSection = sectionService.updateSectionById(idSection, sectionDto);
        SectionDto updatedSectionDto = new SectionDto(updatedSection.getId(), updatedSection.getName(), updatedSection.getDescription(), updatedSection.getLogo());
        return ResponseEntity.ok(updatedSectionDto);
    }

    @PatchMapping("/{idSection}")
    public ResponseEntity<SectionDto> patchSection(@PathVariable Long idSection, @RequestBody Map<String, Object> updates) {
        try {
            Section patchedSection = sectionService.patchSection(idSection, updates);
            SectionDto patchedSectionDto = new SectionDto(patchedSection.getId(), patchedSection.getName(), patchedSection.getDescription(), patchedSection.getLogo());
            return ResponseEntity.ok(patchedSectionDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
