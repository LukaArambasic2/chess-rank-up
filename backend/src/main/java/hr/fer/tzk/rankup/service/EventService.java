package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.EventDTO;
import hr.fer.tzk.rankup.form.EventForm;
import hr.fer.tzk.rankup.mapper.EventMapper;
import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.repository.EventRepository;
import hr.fer.tzk.rankup.repository.EventTypeRepository;
import hr.fer.tzk.rankup.repository.SectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final SectionRepository sectionRepository;
    private final EventTypeRepository eventTypeRepository;

    public EventService(EventRepository eventRepository, SectionRepository sectionRepository, EventTypeRepository eventTypeRepository) {
        this.eventRepository = eventRepository;
        this.sectionRepository = sectionRepository;
        this.eventTypeRepository = eventTypeRepository;
    }

    public ResponseEntity<List<EventDTO>> findAll() {
        List<Event> eventList = eventRepository.findAll();
        List<EventDTO> eventDTOList = eventList.stream().map(EventMapper::toEventDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(eventDTOList);
    }

    public Optional<Event> findByName(String name) {
        return eventRepository.findByName(name);
    }

    public ResponseEntity<EventDTO> findEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            EventDTO eventDTO = EventMapper.toEventDTO(event.get());
            return ResponseEntity.status(HttpStatus.OK).body(eventDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<String> createEvent(EventForm eventForm) {
        Optional<Section> section = sectionRepository.findById(eventForm.getIdSection());
        Optional<EventType> eventType = eventTypeRepository.findById(eventForm.getIdEventType());

        String checkEmptyData = checkSectionAndEventType(section, eventType);
        if (checkEmptyData.length() > 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(checkEmptyData);
        }

        Event newEvent = EventMapper.fromEventForm(eventForm, section.get(), eventType.get());
        newEvent = eventRepository.save(newEvent);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEvent.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }

    public ResponseEntity<String> updateEvent(Long id, EventForm eventForm) {
        Optional<Section> section = sectionRepository.findById(eventForm.getIdSection());
        Optional<EventType> eventType = eventTypeRepository.findById(eventForm.getIdEventType());

        if (eventRepository.existsById(id)) {
            String checkEmptyData = checkSectionAndEventType(section, eventType);
            if (checkEmptyData.length() > 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(checkEmptyData);
            }

            Event event = eventRepository.findById(id).get();
            event.setName(eventForm.getName());
            event.setDate(eventForm.getDate());
            event.setDescription(eventForm.getDescription());
            event.setSection(section.get());
            event.setEventType(eventType.get());

            eventRepository.save(event);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private String checkSectionAndEventType(Optional<Section> section, Optional<EventType> eventType) {
        if (section.isEmpty()) {
            return "Nepostojeća sekcija!";
        }

        if (eventType.isEmpty()) {
            return "Nepostojeći tip eventa!";
        }

        return "";
    }

    public ResponseEntity<Void> deleteEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
