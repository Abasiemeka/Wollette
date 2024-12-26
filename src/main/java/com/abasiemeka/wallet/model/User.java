package com.abasiemeka.wallet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    private String firstName;
    private String lastName;
    private String middleName;

    @Column(unique = true)
    private String email;

    private String password;
    private LocalDate dateOfBirth;
    private String address;
    private String bvn;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;
    private Collection<Object> roles;
}

