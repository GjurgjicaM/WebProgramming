//package mk.finki.ukim.mk.lab.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "events-user")
//public class User {
//    @Id
//    private String username;
//
//    private String password;
//
//    private String name;
//    private String surname;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<EventBooking> eventBookings;
//
//    public User(String username, String password, String name, String surname) {
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.surname = surname;
//    }
//}
