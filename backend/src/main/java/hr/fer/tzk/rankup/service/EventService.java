package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.EventDTO;
import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.repository.EventRepository;
import hr.fer.tzk.rankup.repository.EventTypeRepository;
import hr.fer.tzk.rankup.repository.SectionRepository;
import org.springframework.stereotype.Service;

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

    public Optional<Event> findByName(String name) {
        return eventRepository.findByName(name);
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public boolean createEvent(EventDTO eventDTO) {
        Optional<Section> section = sectionRepository.findById(eventDTO.getIdSection());
        Optional<EventType> eventType = eventTypeRepository.findById(eventDTO.getIdEventType());

        if (section.isPresent() && eventType.isPresent()) {
            Event event = new Event(eventDTO.getName(), eventDTO.getDateFrom(), eventDTO.getDateTo(), eventDTO.getDescription(), section.get(), eventType.get());
            eventRepository.save(event);
            return true;
        } else {
            return false;
        }
    }
}
