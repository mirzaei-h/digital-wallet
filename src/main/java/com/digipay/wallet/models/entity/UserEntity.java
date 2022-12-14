package com.digipay.wallet.models.entity;

import com.digipay.wallet.base.BaseEntity;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USER_TABALE")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique=true, nullable = false)
    private String username;

    @NonNull
    @NotBlank(message = "New password is mandatory")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<WalletEntity> wallets;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<TransactionEntity> transactions;
}
