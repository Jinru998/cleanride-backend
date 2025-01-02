package com.cleanride.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_SEQ_GEN")
    @SequenceGenerator(name = "PAYMENT_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    @Getter
    @Setter
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDERID")
    @Setter
    @Getter
    private Order order;

    @Column(name = "STATUS")
    @Setter
    @Getter
    private String status;

    @Column(name = "AMOUNT")
    @Setter
    @Getter
    private BigDecimal amount;

    @Column(name = "CREATEDAT")
    @Setter
    @Getter
    private Instant createdAt;

    @Column(name = "UPDATEDAT")
    @Setter
    @Getter
    private Instant updatedAt;

    @Column(name = "STRIPECHARGEID")
    @Setter
    @Getter
    private long stripeChargeId;

}
