package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.dto.EventDto;
import hr.fer.tzk.rankup.form.EventDatesForm;
import hr.fer.tzk.rankup.form.EventForm;
import hr.fer.tzk.rankup.mapper.EventMapper;
import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sections/{idSection}/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> findAllEvent(@PathVariable Long idSection) {
        List<EventDto> events = eventService.findAllBySectionId(idSection);
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @GetMapping("/{idEvent}")
    public ResponseEntity<EventDto> findEventById(@PathVariable Long idSection, @PathVariable Long idEvent) {
        Optional<Event> eventOpt = eventService.findEventByIdAndSectionId(idEvent, idSection);
        if (eventOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Event event = eventOpt.get();
        return ResponseEntity.ok(EventMapper.toDto(event));
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@PathVariable Long idSection, @Valid @RequestBody EventForm eventForm) {
        Event newEvent = eventService.createEvent(idSection, eventForm);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEvent.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

    @PutMapping("/{idEvent}")
    public ResponseEntity<Void> updateEvent(@PathVariable Long idSection, @PathVariable Long idEvent, @RequestBody @Valid EventForm eventForm) {
        Optional<Event> updatedEvent = eventService.updateEvent(idSection, idEvent, eventForm);
        if (updatedEvent.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{idEvent}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long idSection, @PathVariable Long idEvent) {
        boolean deleted = eventService.deleteEvent(idSection, idEvent);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventDto>> getAllEventsBetweenDates(@PathVariable Long idSection, @Valid @RequestBody EventDatesForm eventDatesForm) {
        LocalDate dateFrom = eventDatesForm.getDateFrom();
        LocalDate dateTo = eventDatesForm.getDateTo();
        if (dateFrom.isAfter(dateTo)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(eventService.getAllEventsBetweenDates(idSection, dateFrom, dateTo));
    }
}
