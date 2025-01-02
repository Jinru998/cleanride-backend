package com.cleanride.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;


@Entity
@Table(name = "ORDERSTATUS")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERSTATUS_SEQ_GEN")
    @SequenceGenerator(name = "ORDERSTATUS_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDERID")
    @Getter
    @Setter
    private Order order;

    @Getter
    @Setter
    @Column(name = "PREVIOUSSTATUS")
    private String previousStatus;

    @Column(name = "NEWSTATUS")
    @Getter
    @Setter
    private String newStatus;

    @Column(name = "CREATEDAT")
    @Getter
    @Setter
    private Instant createdAt;

}
