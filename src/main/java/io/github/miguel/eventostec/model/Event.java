package io.github.miguel.eventostec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    private Date date;

    private boolean remote;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "event_url")
    private String eventUrl;


}
