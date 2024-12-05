package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();

    Optional<Event> findById(Long id);

    Optional<Event> findByName(String name);

    List<Event> searchEvents(String text);

    List<Event> findByLocation(Long locationId);

//    List<Event> searchByCategory(String category);

    List<Event> findByCategory(Long categoryId);

    void save(Event event);

    void deleteById(Long id);

    Event updateEvent(Long id, String name, String description, Double popularityScore, Location location,
                      int numTickets);
}
