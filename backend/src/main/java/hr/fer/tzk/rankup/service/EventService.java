package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.Event;
import hr.fer.tzk.rankup.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Optional<Event> findByName(String name) {
        return eventRepository.findByName(name);
    }
}
