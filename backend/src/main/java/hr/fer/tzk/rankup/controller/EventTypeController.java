package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.EventTypeDTO;
import hr.fer.tzk.rankup.form.EventTypeForm;
import hr.fer.tzk.rankup.service.EventTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-type")
public class EventTypeController {
    private EventTypeService eventTypeService;

    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping
    public ResponseEntity<List<EventTypeDTO>> getAllEventType() {
        return eventTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventTypeDTO> findEventTypeById(@PathVariable Long id) {
        return eventTypeService.findEventTypeById(id);
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
