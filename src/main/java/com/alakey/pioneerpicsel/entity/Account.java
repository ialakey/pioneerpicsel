package com.alakey.pioneerpicsel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_info_id", unique = true)
    private UserInfo userInfo;

    private BigDecimal balance;

    @Column(name = "initial_balance")
    private BigDecimal initialBalance;
}
