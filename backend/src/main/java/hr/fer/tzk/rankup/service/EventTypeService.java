package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.repository.EventTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class EventTypeService {
    private final EventTypeRepository eventTypeRepository;

    public EventTypeService(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    public ResponseEntity<List<EventType>> findAll() {
        return ResponseEntity.ok(eventTypeRepository.findAll());
    }

    public ResponseEntity findEventTypeById(Long id) {
        Optional<EventType> eventType = eventTypeRepository.findById(id);
        if (eventType.isPresent()) {
            return ResponseEntity.ok(eventType.get());
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity createEventType(EventType newEventType) {
        if (newEventType.getName() == null || newEventType.getName().isBlank()) {
            return ResponseEntity.badRequest().body("Ime ne smije biti prazno");
        }
        if (newEventType.getDefaultPoints() < 0) {
            return ResponseEntity.badRequest().body("Bodovi ne smiju biti negativni");
        }

        EventType eventType = eventTypeRepository.save(newEventType);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventType.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity updateEventType(Long id, EventType newEventType) {
        if (newEventType.getName() == null || newEventType.getName().isBlank()) {
            return ResponseEntity.badRequest().body("Ime ne smije biti prazno");
        }
        if (newEventType.getDefaultPoints() < 0) {
            return ResponseEntity.badRequest().body("Bodovi ne smiju biti negativni");
        }

        Optional<EventType> eventType = eventTypeRepository.findById(id);
        if (eventType.isPresent()) {
            EventType eventTypeForSave = eventType.get();
            eventTypeForSave.setName(newEventType.getName());
            eventTypeForSave.setDefaultPoints(newEventType.getDefaultPoints());
            eventTypeRepository.save(eventTypeForSave);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity deleteEventType(Long id) {
        Optional<EventType> eventType = eventTypeRepository.findById(id);
        if (eventType.isPresent()) {
            eventTypeRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
