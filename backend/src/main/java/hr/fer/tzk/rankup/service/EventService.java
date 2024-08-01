package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.EventDTO;
import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.repository.EventRepository;
import hr.fer.tzk.rankup.repository.EventTypeRepository;
import hr.fer.tzk.rankup.repository.SectionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private EventRepository eventRepository;
    private SectionRepository sectionRepository;
    private EventTypeRepository eventTypeRepository;

    public EventService(EventRepository eventRepository, SectionRepository sectionRepository, EventTypeRepository eventTypeRepository) {
        this.eventRepository = eventRepository;
        this.sectionRepository = sectionRepository;
        this.eventTypeRepository = eventTypeRepository;
    }

    public ResponseEntity<List<Event>> findAll() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    public Optional<Event> findByName(String name) {
        return eventRepository.findByName(name);
    }

    public ResponseEntity findEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity createEvent(EventDTO eventDTO) {
        Optional<Section> section = sectionRepository.findById(eventDTO.getIdSection());
        Optional<EventType> eventType = eventTypeRepository.findById(eventDTO.getIdEventType());

        if (section.isEmpty() || eventType.isEmpty()) {
            return ResponseEntity.badRequest().body("Nepostojeća sekcija ili tip eventa");
        }

        if (eventDTO.getName() == null || eventDTO.getName().isBlank() ||
                eventDTO.getDate() == null ||
                eventDTO.getDescription() == null || eventDTO.getDescription().isBlank()
        ) {
            return ResponseEntity.badRequest().body("Svi atributi moraju biti popunjeni");
        }

        Event newEvent = new Event(eventDTO.getName(), eventDTO.getDate(), eventDTO.getDescription(), section.get(), eventType.get());
        newEvent = eventRepository.save(newEvent);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEvent.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity updateEvent(Long id, EventDTO eventDTO) {
        if (eventDTO.getName() == null || eventDTO.getName().isBlank() ||
                eventDTO.getDate() == null ||
                eventDTO.getDescription() == null || eventDTO.getDescription().isBlank()
        ) {
            return ResponseEntity.badRequest().body("Svi atributi moraju biti popunjeni");
        }

        if (eventRepository.existsById(id)) {
            if (!sectionRepository.existsById(eventDTO.getIdSection()) ||
                    !eventTypeRepository.existsById(eventDTO.getIdEventType())
            ) {
                return ResponseEntity.badRequest().body("Nepostojeća sekcija ili tip eventa");
            }

            Event newEvent = eventRepository.findById(id).get();
            newEvent.setName(eventDTO.getName());
            newEvent.setDescription(eventDTO.getDescription());
            newEvent.setDate(eventDTO.getDate());
            newEvent.setSection(sectionRepository.findById(eventDTO.getIdSection()).get());
            newEvent.setEventType(eventTypeRepository.findById(eventDTO.getIdEventType()).get());
            eventRepository.save(newEvent);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity deleteEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
