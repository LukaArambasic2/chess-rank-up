package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.EventTypeDTO;
import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.service.EventTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-type")
public class EventTypeController {

    private EventTypeService eventTypeService;

    public EventTypeController (EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping
    public ResponseEntity<List<EventType>> getEventTypes() {
        return ResponseEntity.ok(eventTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity createEventType(@RequestBody EventTypeDTO eventTypeDTO) {
        eventTypeService.createEventType(eventTypeDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
