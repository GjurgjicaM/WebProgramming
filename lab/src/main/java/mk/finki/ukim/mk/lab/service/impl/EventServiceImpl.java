package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exceptions.CategoryNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.LocationNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.CategoryRepository;
import mk.finki.ukim.mk.lab.repository.jpa.EventRepository;
import mk.finki.ukim.mk.lab.repository.jpa.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> findByName(String name) {
        return this.eventRepository.findAll().stream().filter(e->e.getName().equals(name)).findFirst();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.findAllByNameLike("%" + text + "%");
    }

    @Override
    public List<Event> findByLocation(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }

    @Override
    public List<Event> findByCategory(Long categoryId) {
        return eventRepository.findAllByCategory_Id(categoryId);
    }

    @Override
    public void save(String name, String description, double popularityScore, Long categoryId, Long locationId, int numTickets) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        this.eventRepository.save(new Event(name, description, popularityScore, category.getId(), location.getId(), numTickets));
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public Event updateEvent(Long id, String name, String description, Double popularityScore, Location location,
                             int numTickets) {
        Event event = this.eventRepository.findById(id).get();

        if (event != null) {
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setLocation(location);
            event.setNumTickets(numTickets);

            this.eventRepository.save(event);
        }
        return event;
    }
}
