package com.abasiemeka.wallet.model;

import com.abasiemeka.wallet.model.enums.TRANSACTIONTYPE;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated
    private TRANSACTIONTYPE transactiontype;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String description;
    
    
}

