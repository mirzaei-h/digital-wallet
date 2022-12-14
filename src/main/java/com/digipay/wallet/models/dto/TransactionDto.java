package com.digipay.wallet.models.dto;

import com.digipay.wallet.base.BaseDto;
import com.digipay.wallet.models.entity.UserEntity;
import com.digipay.wallet.models.entity.WalletEntity;
import com.digipay.wallet.models.enums.TransactionType;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto extends BaseDto {
    private Long transactionId;

    @Digits(fraction = 0, integer = 10)
    @Size(min = 16, max = 16)
    private String accountNumber;

    @Min(0)
    private Double amount;
    private TransactionType type;

    @Size(max = 16)
    private String description;
    private String walletName;
    private UserDto user;

}
