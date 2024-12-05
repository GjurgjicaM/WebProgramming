package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exceptions.CategoryNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.LocationNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.CategoryRepository;
import mk.finki.ukim.mk.lab.service.CategoryService;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;
    private final CategoryService categoryService;

    public EventController(EventService eventService, LocationService locationService, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String search,
                                @RequestParam(required = false) Long searchByCategory,
                                Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        } else if (search != null && !search.isEmpty()) {
            model.addAttribute("events", eventService.searchEvents(search));
        } else if (searchByCategory != null) {
            model.addAttribute("events", eventService.findByCategory(searchByCategory));
        } else {
            model.addAttribute("events", eventService.listAll());
        }
        model.addAttribute("categories", categoryService.findAll());
        return "listEvents";
    }

    @GetMapping("/add-form")
    public String getAddEventPage(Model model) {
        List<Location> locations = locationService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("locations", locations);
        model.addAttribute("categories", categories);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Long categoryId,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId) {

        Event event = new Event(name, description, popularityScore, categoryService.findById(categoryId).get(), locationService.findById(locationId).get(), 100);
        this.eventService.save(event);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        if (eventService.findById(id).stream().findFirst().isPresent()) {
            Event event = eventService.findById(id).stream().findFirst().get();
            List<Location> locations = locationService.findAll();
            List<Category> categories = categoryService.findAll();
            model.addAttribute("locations", locations);
            model.addAttribute("categories", categories);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=Event not found";
    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Long categoryId,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId) {
        if (this.eventService.findById(eventId).stream().findFirst().isPresent()) {
            Event event = this.eventService.findById(eventId).stream().findFirst().get();
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setLocation(this.locationService.findById(locationId).stream().findFirst().get());
            event.setCategory(this.categoryService.findById(categoryId).stream().findFirst().get());
            eventService.save(event);
        }
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        if (eventService.findById(id).isPresent()) {
            this.eventService.deleteById(id);
            return "redirect:/events";
        } else return "redirect:/events?error=Event not found";
    }

    @PostMapping("/search")
    public String getSearchedEvents(@RequestParam String search) {
        if (search != null && !search.isEmpty())
            return "redirect:/events?search=" + search;
        return "redirect:/events";
    }

    @PostMapping("/searchByCategory")
    public String getSearchedByCategoryEvents(@RequestParam Long searchByCategory) {
        if (categoryService.findById(searchByCategory).isPresent()) {
            Category category = categoryService.findById(searchByCategory).get();
            return "redirect:/events?searchByCategory=" + searchByCategory;
        }
        return "redirect:/events";
    }

    @GetMapping("/review-form/{id}")
    public String getReviewForm(@PathVariable Long id, Model model) {
        Optional<Event> event = eventService.findById(id);
        if (event.isPresent()) {
            model.addAttribute("event", event.get());
            return "review-form";
        }
        return "redirect:/events?error=Event not found";
    }

}
