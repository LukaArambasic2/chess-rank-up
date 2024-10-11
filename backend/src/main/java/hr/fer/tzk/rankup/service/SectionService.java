package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.SectionDto;
import hr.fer.tzk.rankup.form.SectionForm;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.repository.SectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }
    public List<Section> findAllSections() {
        return sectionRepository.findAll();
    }

    public Optional<Section> findSectionById(Long idSection) {
        return sectionRepository.findById(idSection);
    }

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    public Optional<Section> findSectionByName(String name) {
        return sectionRepository.findByName(name);
    }

    public void deleteSectionById(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section with id " + id + " not found"));
        sectionRepository.delete(section);
    }

    public Section updateSectionById(Long id, SectionForm form) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section with id " + id + " not found"));

        BeanUtils.copyProperties(form, section, "id");
        return sectionRepository.save(section);
    }

    public Section patchSection(Long id, Map<String, Object> updates) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section with id " + id + " not found"));
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Section.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, section, value);
            }
        });

        return sectionRepository.save(section);
    }
}
