package com.digipay.wallet.models.entity;

import com.digipay.wallet.base.BaseEntity;
import com.digipay.wallet.models.enums.WalletStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WALLET",uniqueConstraints = @UniqueConstraint(columnNames = {"walletName", "user_id"}))
public class WalletEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    @Column(nullable = false)
    private String walletName;
    private Double balance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WalletStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet")
    private List<TransactionEntity> transactions;
}
