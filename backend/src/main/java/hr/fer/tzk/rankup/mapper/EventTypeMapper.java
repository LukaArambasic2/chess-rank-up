package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.dto.EventTypeDTO;
import hr.fer.tzk.rankup.form.EventTypeForm;
import hr.fer.tzk.rankup.model.EventType;

public class EventTypeMapper {
    public static EventTypeDTO toEventDTO(EventType eventType) {
        if (eventType == null) {
            return null;
        }

        EventTypeDTO eventTypeDTO = new EventTypeDTO();
        eventTypeDTO.setName(eventType.getName());
        eventTypeDTO.setDefaultPoints(eventType.getDefaultPoints());

        return eventTypeDTO;
    }

    public static EventType fromEventForm(EventTypeForm eventTypeForm) {
        EventType eventType = new EventType();
        eventType.setName(eventTypeForm.getName());
        eventType.setDefaultPoints(eventTypeForm.getDefaultPoints());

        return eventType;
    }
}
