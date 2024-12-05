package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootsrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//@Repository
public class InMemoryEventRepository {

    public InMemoryEventRepository() {
    }

    public List<Event> findAll() {
        return DataHolder.events.stream().toList();
    }

    public Optional<Event> findById(Long id) {
        return DataHolder.events.stream().filter(event -> event.getId().equals(id)).findFirst();
    }

    public Optional<Event> findByName(String name) {
        return DataHolder.events.stream().filter(event -> event.getName().equals(name)).findFirst();
    }


    public List<Event> searchEvents(String text) {
        List<Event> initial = DataHolder.events.stream().filter(e -> e.getName().contains(text) || e.getDescription().contains(text))
                .toList();
        if (initial.isEmpty()) {
            initial = DataHolder.events.stream().filter(e -> e.getPopularityScore() >= Double.parseDouble(text))
                    .toList();
        }
        return initial;
    }

    public List<Event> searchByCategory(String text) {
        return DataHolder.events.stream().filter(event -> event.getCategory().getCategory().equals(text)).collect(Collectors.toList());
    }

    public Optional<Event> saveEvent(String name, String description, double popularityScore, Category category, Location location, int numTickets) {
        if (category == null || location == null) {
            throw new IllegalArgumentException();
        }
        Event event = new Event((long) (Math.random() * 1000), name, description, popularityScore, category, location, numTickets);
        DataHolder.events.removeIf(event1 -> event1.getName().equals(name));
        DataHolder.events.add(event);
        return Optional.of(event);
    }

    public void deleteById(Long id) {
        DataHolder.events.removeIf(event -> event.getId().equals(id));
    }

}
