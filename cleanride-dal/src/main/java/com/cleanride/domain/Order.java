package com.cleanride.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQ_GEN")
    @SequenceGenerator(name = "ORDERS_SEQ", allocationSize = 1)
    @Getter
    @Setter
    private Long id;

    @Column(name = "STATUS")
    @Getter
    @Setter
    private String status;

    @Column(name = "CREATEDAT")
    @Getter
    @Setter
    private Instant createdAt;

    @Column(name = "UPDATEDAT")
    @Getter
    @Setter
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "USERID", nullable = false)
    @Getter
    @Setter
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PAYMENTID")
    @Getter
    @Setter
    private Payment payment;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "RIDEID")
    @Getter
    @Setter
    private List<Ride> rides;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "NOTIFICATIONID")
    @Getter
    @Setter
    private List<Notification> notifications;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDERSTATUSID")
    @Getter
    @Setter
    private List<OrderStatus> orderStatuses;
}
