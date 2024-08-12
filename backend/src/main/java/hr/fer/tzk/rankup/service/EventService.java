package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.EventDto;
import hr.fer.tzk.rankup.form.EventDatesForm;
import hr.fer.tzk.rankup.form.EventForm;
import hr.fer.tzk.rankup.mapper.EventMapper;
import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.model.SectionMember;
import hr.fer.tzk.rankup.repository.EventRepository;
import hr.fer.tzk.rankup.repository.EventTypeRepository;
import hr.fer.tzk.rankup.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final SectionRepository sectionRepository;
    private final EventTypeRepository eventTypeRepository;
    private final SectionService sectionService;
    private final EventTypeService eventTypeService;

    @Autowired
    public EventService(EventRepository eventRepository, SectionRepository sectionRepository, EventTypeRepository eventTypeRepository, SectionService sectionService, EventTypeService eventTypeService) {
        this.eventRepository = eventRepository;
        this.sectionRepository = sectionRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.sectionService = sectionService;
        this.eventTypeService = eventTypeService;
    }

    public List<EventDto> findAllBySectionId(Long idSection) {
        List<Event> events = eventRepository.findAllBySection_Id(idSection);
        return events.stream()
                .map(EventMapper::toDto).
                toList();
    }

    public Optional<Event> findByName(String name) {
        return eventRepository.findByName(name);
    }

    public Optional<Event> findEventByIdAndSectionId(Long idEvent, Long idSection) {
        return eventRepository.findByIdAndSection_Id(idEvent, idSection);
    }

    /*
    public ResponseEntity<String> createEvent(Long idSection, EventForm eventForm) {
        Optional<Section> section = sectionService.findSectionById(idSection);
        Optional<EventType> eventType = eventTypeRepository.findById(eventForm.getIdEventType());

        String checkEmptyData = checkSectionAndEventType(section, eventType);
        if (checkEmptyData.length() > 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(checkEmptyData);
        }

        Event newEvent = EventMapper.fromForm(eventForm, section.get(), eventType.get());
        newEvent = eventRepository.save(newEvent);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEvent.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).build();
    }*/

    public Event createEvent(Long idSection, EventForm eventForm) {
        Long idEventType = eventForm.getIdEventType();
        Optional<EventType> eventTypeOpt = eventTypeService.findEventTypeById(idEventType);
        Optional<Section> sectionOpt = sectionService.findSectionById(idSection);
        if (sectionOpt.isEmpty() || eventTypeOpt.isEmpty()) {
            return null;
        }

        Section section = sectionOpt.get();
        EventType eventType = eventTypeOpt.get();

        Event newEvent = EventMapper.fromForm(eventForm, section, eventType);
        return eventRepository.save(newEvent);
    }

    public Optional<Event> updateEvent(Long idSection, Long idEvent, EventForm eventForm) {
        Optional<Event> existingEvent = eventRepository.findByIdAndSection_Id(idEvent, idSection);
        Optional<Section> sectionOpt = sectionRepository.findById(idSection);
        Optional<EventType> eventTypeOpt = eventTypeRepository.findById(eventForm.getIdEventType());

        if (existingEvent.isEmpty() || sectionOpt.isEmpty() || eventTypeOpt.isEmpty()) {
            return Optional.empty();
        }

        Section section = sectionOpt.get();
        EventType eventType = eventTypeOpt.get();
        Event newEvent = eventRepository.save(EventMapper.fromForm(eventForm, section, eventType));
        return Optional.of(newEvent);
    }

    public boolean deleteEvent(Long idSection, Long idEvent) {
        Optional<Event> event = eventRepository.findByIdAndSection_Id(idEvent, idSection);
        if (event.isPresent()) {
            eventRepository.deleteById(idEvent);
            return true;
        }
        return false;
    }

    public List<EventDto> getAllEventsBetweenDates(Long idSection, LocalDate dateFrom, LocalDate dateTo) {
        List<Event> events = eventRepository.findAllByDateBetweenAndSection_IdOrderByDate(dateFrom, dateTo, idSection);
        return events.stream()
                .map(EventMapper::toDto)
                .sorted(Comparator.comparing(EventDto::getDate))
                .toList();
    }
}
