package mk.finki.ukim.mk.lab.bootsrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.*;
import mk.finki.ukim.mk.lab.repository.jpa.CategoryRepository;
import mk.finki.ukim.mk.lab.repository.jpa.EventRepository;
import mk.finki.ukim.mk.lab.repository.jpa.LocationRepository;
//import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class DataHolder {
    public static final List<Event> events = new ArrayList<>(10);
    public static final List<EventBooking> MyBookings = new ArrayList<>();
    public static final List<Category> categories = new ArrayList<>();
    public static final List<Location> locations = new ArrayList<>();
//    public static final List<User> users = new ArrayList<>();

    public final LocationRepository locationRepository;
    public final CategoryRepository categoryRepository;
    public final EventRepository eventRepository;
//    public final UserRepository userRepository;

    public DataHolder(LocationRepository locationRepository, CategoryRepository categoryRepository, EventRepository eventRepository) {
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
//        this.userRepository = userRepository;

        categories.add(new Category((long) 1,"IT"));
        categories.add(new Category((long) 2,"Fashion"));
        categories.add(new Category((long) 3,"Music"));
        categories.add(new Category((long) 4,"Sport"));
        if (locationRepository.count() == 0){
            categoryRepository.saveAll(categories);
        }

        locations.add(new Location((long) 1,"Tech Conference Hall"));
        locations.add(new Location((long) 2, "Downtown Fashion Center"));
        locations.add(new Location((long) 3, "City Park Amphitheater"));
        if(locationRepository.count() ==0){
            locationRepository.saveAll(locations);
        }

        events.add(new Event((long) 1,"AI Tech Summit", "A gathering of technology enthusiast", 9.5, categories.get(0),locations.get(0),100));
        events.add(new Event((long) 2,"Fashion Week", "A week-long event showcasing the latest trends in fashion", 10, categories.get(1),locations.get(1),150));
        events.add(new Event((long) 3,"World Music Festival", "An international music festival", 8.2, categories.get(2),locations.get(2),200));
        if (eventRepository.count()==0){
            eventRepository.saveAll(events);
        }

//        users.add(new User("gjurgjica", "gjm", "gjurgjica" , "mladenovska"));
//        if(userRepository.count() ==0){
//            userRepository.saveAll(users);
//        }

    }
}
