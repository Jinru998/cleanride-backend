package com.cleanride.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "RIDE")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RIDE_SEQ_GEN")
    @SequenceGenerator(name = "RIDE_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDERID")
    @Getter
    @Setter
    private Order order;

    @Column(name = "RIDETYPE")
    @Getter
    @Setter
    private String rideType;

    @Column(name = "UBERRIDEID")
    @Getter
    @Setter
    private String uberRideId;

    @Column(name = "STATUS")
    @Getter
    @Setter
    private String status;

    @Column(name = "SCHEDULEDTIME")
    @Getter
    @Setter
    private Instant scheduledTime;

    @Column(name = "CREATEDAT")
    @Getter
    @Setter
    private Instant createdAt;

    @Column(name = "UPDATEDAT")
    @Getter
    @Setter
    private Instant updatedAt;

    @Column(name = "ADDRESS")
    @Getter
    @Setter
    private String address;
}
