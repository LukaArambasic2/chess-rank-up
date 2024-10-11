package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.service.EventTypeService;
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
    public ResponseEntity<List<EventType>> getAllEventType() {
        return eventTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findEventTypeById(@PathVariable Long id) {
        return eventTypeService.findEventTypeById(id);
    }

    @PostMapping
    public ResponseEntity createEventType(@RequestBody EventType eventType) {
        return eventTypeService.createEventType(eventType);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEventType(@PathVariable Long id, @RequestBody EventType eventType) {
        System.out.println("Prije ulaska u service");
        return eventTypeService.updateEventType(id, eventType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEventType(@PathVariable Long id) {
        return eventTypeService.deleteEventType(id);
    }
}
