package io.github.miguel.eventostec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
public class Address {

    public Address(String uf, String city, Event event) {
        this.uf = uf;
        this.city = city;
        this.event = event;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String uf;

    private String city;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
