package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.EventDTO;
import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvent() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findEventById(@PathVariable Long id) {
        return eventService.findEventById(id);
    }

    @PostMapping()
    public ResponseEntity createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(id, eventDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }
}
