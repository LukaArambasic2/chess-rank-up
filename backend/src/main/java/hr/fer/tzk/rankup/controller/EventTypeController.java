package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.EventTypeDto;
import hr.fer.tzk.rankup.form.EventTypeForm;
import hr.fer.tzk.rankup.mapper.EventTypeMapper;
import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.service.EventTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event-type")
public class EventTypeController {
    private final EventTypeService eventTypeService;

    @Autowired
    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping
    public ResponseEntity<List<EventTypeDto>> getAllEventType() {
        List<EventTypeDto> eventTypes = eventTypeService.findAll()
                .stream()
                .map(EventTypeMapper::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(eventTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventTypeDto> findEventTypeById(@PathVariable Long id) {
        Optional<EventType> eventTypeOpt = eventTypeService.findEventTypeById(id);
        if (eventTypeOpt.isPresent()) {
            EventTypeDto eventTypeDTO = EventTypeMapper.toDto(eventTypeOpt.get());
            return ResponseEntity.status(HttpStatus.OK).body(eventTypeDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createEventType(@RequestBody @Valid EventTypeForm eventTypeForm) {
        return eventTypeService.createEventType(eventTypeForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEventType(@PathVariable Long id, @RequestBody @Valid EventTypeForm eventTypeForm) {
        return eventTypeService.updateEventType(id, eventTypeForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventType(@PathVariable Long id) {
        return eventTypeService.deleteEventType(id);
    }
}
