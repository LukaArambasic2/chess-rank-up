package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.EventDTO;
import hr.fer.tzk.rankup.form.EventForm;
import hr.fer.tzk.rankup.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> findAllEvent() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findEventById(@PathVariable Long id) {
        return eventService.findEventById(id);
    }

    @PostMapping()
    public ResponseEntity<String> createEvent(@RequestBody @Valid EventForm eventForm) {
        return eventService.createEvent(eventForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEvent(@PathVariable Long id, @RequestBody @Valid EventForm eventForm) {
        return eventService.updateEvent(id, eventForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }
}
