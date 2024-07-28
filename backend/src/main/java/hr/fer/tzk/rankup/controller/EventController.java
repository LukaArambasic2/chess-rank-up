package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.EventDTO;
import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event.get());
    }

    @PostMapping()
    public ResponseEntity createEvent(@RequestBody EventDTO eventDTO) {
        if (eventService.createEvent(eventDTO)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
