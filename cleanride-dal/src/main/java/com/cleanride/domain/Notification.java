package com.cleanride.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "NOTIFICATION")
public class Notification {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTIFICATION_SEQ_GEN")
    @SequenceGenerator(name = "NOTIFICATION_SEQ", allocationSize = 1)
    @Getter
    @Setter
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDERID")
    @Getter
    @Setter
    private Order order;

    @Column(name = "TYPE")
    @Getter
    @Setter
    private String type;

    @Column(name = "CHANNEL")
    @Getter
    @Setter
    private String channel;

    @Column(name = "RECIPIENT")
    @Getter
    @Setter
    private String recipient;

    @Column(name = "SUBJECT")
    @Getter
    @Setter
    private String subject;

    @Column(name = "MESSAGE")
    @Getter
    @Setter
    private String message;

    @Column(name = "STATUS")
    @Getter
    @Setter
    private String status;

    @Column(name = "SENTAT")
    @Getter
    @Setter
    private Instant sentAt;

    @Column(name = "CREATEDAT")
    @Getter
    @Setter
    private Instant createdAt;
}
