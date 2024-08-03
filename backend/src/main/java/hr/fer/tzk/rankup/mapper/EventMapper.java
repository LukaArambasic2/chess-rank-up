package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.dto.EventDTO;
import hr.fer.tzk.rankup.form.EventForm;
import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.model.Section;

public class EventMapper {
    public static EventDTO toEventDTO(Event event) {
        if (event == null) {
            return null;
        }

        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventName(event.getName());
        eventDTO.setDate(event.getDate());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setEventTypeName(event.getEventType().getName());

        return eventDTO;
    }

    public static Event fromEventForm(EventForm eventForm, Section section, EventType eventType) {
        Event event = new Event();
        event.setEventType(eventType);
        event.setSection(section);
        event.setName(eventForm.getName());
        event.setDescription(eventForm.getDescription());
        event.setDate(eventForm.getDate());

        return event;
    }
}
