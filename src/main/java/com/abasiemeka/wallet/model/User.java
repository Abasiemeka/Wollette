package com.abasiemeka.wallet.model;

import com.abasiemeka.wallet.model.enums.ROLE;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;
    
    @Email
    @Column(unique = true)
    private String email;

    private String password;
    
    @DateTimeFormat()
    private LocalDate dateOfBirth;
    private String address;
    private String bvn;

    @OneToOne(targetEntity = Wallet.class, cascade = CascadeType.ALL)
    private Wallet wallet;
    
    private UserDetails userDetails;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<ROLE> roles;
}

