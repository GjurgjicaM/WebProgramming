package mk.finki.ukim.mk.lab.bootsrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.Location;
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

    public DataHolder() {

        categories.add(new Category((long) 1,"IT"));
        categories.add(new Category((long) 2,"Fashion"));
        categories.add(new Category((long) 3,"Music"));
        categories.add(new Category((long) 4,"Sport"));


        locations.add(new Location((long) 1,"Tech Conference Hall"));
        locations.add(new Location((long) 2, "Downtown Fashion Center"));
        locations.add(new Location((long) 3, "City Park Amphitheater"));

        events.add(new Event((long) 1,"AI Tech Summit", "A gathering of technology enthusiast", 9.5, categories.get(0),locations.get(0),100));
        events.add(new Event((long) 2,"Fashion Week", "A week-long event showcasing the latest trends in fashion", 10, categories.get(1),locations.get(1),150));
        events.add(new Event((long) 3,"World Music Festival", "An international music festival", 8.2, categories.get(2),locations.get(2),200));

    }
}
