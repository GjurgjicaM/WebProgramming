package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    bez dopolnitelni tabeli tuku so generirano id
    private Long Id;

    private String name;
    private String description;
    private double popularityScore;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Location location;

    private int numTickets;


    public Event(String name, String description, double popularityScore, Long categoryId, Long locationId, int numTickets) {
    }

}

//    Изменете ја класата Event во пакетот mk.ukim.finki.wp.lab.model:
//
//        Анотирајте ја со @Entity за да стане JPA ентитет.
//        Обезбедете уникатно id за секој настан, анотирајќи го со @Id и @GeneratedValue.