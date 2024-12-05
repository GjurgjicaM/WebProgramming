package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EventBooking {
    @Id
    @GeneratedValue
    private String eventName;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberOfTickets;

//    @ManyToOne
//    private User user;
}
