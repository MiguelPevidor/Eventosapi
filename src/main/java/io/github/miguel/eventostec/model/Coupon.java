package io.github.miguel.eventostec.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "cupom")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    private Integer discount;

    private String code;

    private Date valid;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;
}
