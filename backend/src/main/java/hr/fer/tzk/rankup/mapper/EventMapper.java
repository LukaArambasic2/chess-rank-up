package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.dto.EventDto;
import hr.fer.tzk.rankup.form.EventForm;
import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.model.Section;

public class EventMapper {
    public static EventDto toDto(Event event) {
        if (event == null) {
            return null;
        }

        EventDto eventDTO = new EventDto();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDate(event.getDate());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setEventTypeName(event.getEventType().getName());

        return eventDTO;
    }

    public static Event fromForm(EventForm eventForm, Section section, EventType eventType) {
        if (eventForm == null || section == null || eventType == null) {
            return null;
        }
        
        Event event = new Event();
        event.setEventType(eventType);
        event.setSection(section);
        event.setName(eventForm.getName());
        event.setDescription(eventForm.getDescription());
        event.setDate(eventForm.getDate());

        return event;
    }
}
