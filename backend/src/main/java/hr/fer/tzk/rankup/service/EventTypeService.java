package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.EventTypeDto;
import hr.fer.tzk.rankup.form.EventTypeForm;
import hr.fer.tzk.rankup.mapper.EventTypeMapper;
import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.repository.EventTypeRepository;
import org.springframework.http.HttpStatus;
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

    public List<EventType> findAll() {
        return eventTypeRepository.findAll();
    }

    public Optional<EventType> findEventTypeById(Long id) {
        return eventTypeRepository.findById(id);
    }

    public ResponseEntity<Void> createEventType(EventTypeForm eventTypeForm) {
        EventType eventType = EventTypeMapper.fromForm(eventTypeForm);

        eventType = eventTypeRepository.save(eventType);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventType.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

    public ResponseEntity<Void> updateEventType(Long id, EventTypeForm eventTypeForm) {
        if (eventTypeRepository.existsById(id)) {
            EventType eventType = eventTypeRepository.findById(id).get();
            eventType.setName(eventTypeForm.getName());
            eventType.setDefaultPoints(eventTypeForm.getDefaultPoints());
            eventTypeRepository.save(eventType);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<Void> deleteEventType(Long id) {
        Optional<EventType> eventType = eventTypeRepository.findById(id);
        if (eventType.isPresent()) {
            eventTypeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
