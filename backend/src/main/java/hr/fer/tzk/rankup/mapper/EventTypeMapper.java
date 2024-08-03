package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.dto.EventTypeDto;
import hr.fer.tzk.rankup.form.EventTypeForm;
import hr.fer.tzk.rankup.model.EventType;

public class EventTypeMapper {
    public static EventTypeDto toDto(EventType eventType) {
        if (eventType == null) {
            return null;
        }

        EventTypeDto dto = new EventTypeDto();
        dto.setName(eventType.getName());
        dto.setDefaultPoints(eventType.getDefaultPoints());

        return dto;
    }

    public static EventType fromForm(EventTypeForm form) {
        EventType eventType = new EventType();
        eventType.setName(form.getName());
        eventType.setDefaultPoints(form.getDefaultPoints());

        return eventType;
    }
}
