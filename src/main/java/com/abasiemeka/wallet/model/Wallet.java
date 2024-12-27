package com.abasiemeka.wallet.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, mappedBy = "wallet")
    @JoinColumn(name = "owner")
    private User user;

    private BigDecimal balance;
    
    private String tier;
}

