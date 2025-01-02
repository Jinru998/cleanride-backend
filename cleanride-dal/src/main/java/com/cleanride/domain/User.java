package com.cleanride.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ_GEN")
    @SequenceGenerator(name = "USERS_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    @Getter
    @Setter
    private Long id;

    @OneToMany
    @JoinColumn(name = "ORDERID")
    @Getter
    @Setter
    private List<Order> orders;

    @Column(name = "CREATEDAT")
    @Getter
    @Setter
    private Instant createdAt;

    @Column(name = "UPDATEDAT")
    @Getter
    @Setter
    private Instant updatedAt;

    @Column(name = "EMAIL", nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(name = "PHONENUMBER")
    @Getter
    @Setter
    private String phoneNumber;

    @Column(name = "FIREBASEID")
    @Getter
    @Setter
    private String firebaseId;

    @Column(name = "ADDRESS")
    @Getter
    @Setter
    private String address;

}
