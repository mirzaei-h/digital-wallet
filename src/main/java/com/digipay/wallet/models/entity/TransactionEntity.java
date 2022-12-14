package com.digipay.wallet.models.entity;

import com.digipay.wallet.base.BaseEntity;
import com.digipay.wallet.models.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Digits(fraction = 0, integer = 10)
    @Size(min = 16, max = 16)
    private String accountNumber;

    @Min(0)
    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Size(max = 16)
    private String description;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private WalletEntity wallet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
