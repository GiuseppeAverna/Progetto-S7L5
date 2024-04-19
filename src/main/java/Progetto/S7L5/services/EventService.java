package Progetto.S7L5.services;

import Progetto.S7L5.entities.Event;
import Progetto.S7L5.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Ottieni tutti gli eventi
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Ottieni un evento per ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Crea un nuovo evento
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Aggiorna un evento esistente
    public Event updateEvent(Long id, Event event) {
        if (eventRepository.existsById(id)) {
            event.setId(id); // Assicura che l'ID corretto sia impostato
            return eventRepository.save(event);
        }
        return null; // O lancia un'eccezione se desiderato
    }

    // Elimina un evento
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
