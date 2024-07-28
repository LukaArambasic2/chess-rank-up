package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.EventTypeDTO;
import hr.fer.tzk.rankup.model.EventType;
import hr.fer.tzk.rankup.repository.EventTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeService {
    private EventTypeRepository eventTypeRepository;

    public EventTypeService(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    public List<EventType> findAll() {
        return eventTypeRepository.findAll();
    }

    public void createEventType(EventTypeDTO eventTypeDTO) {
        EventType eventType = new EventType(eventTypeDTO.getName(), eventTypeDTO.getDefaultPoints());
        eventTypeRepository.save(eventType);
    }

}
