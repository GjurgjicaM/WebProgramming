package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    @OneToMany(mappedBy = "location")
    private List<Event> events;

    public Location(Long id, String address) {
        this.id = id;
        this.address = address;
    }
}

//
//    Изменете ја класата Location:
//
//        Анотирајте ја со @Entity.
//        Обезбедете уникатно id со @Id и @GeneratedValue.
//        Додадете ја релацијата со Event (@OneToMany).