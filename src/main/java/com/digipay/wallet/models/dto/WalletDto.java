package com.digipay.wallet.models.dto;

import com.digipay.wallet.base.BaseDto;
import com.digipay.wallet.models.enums.WalletStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto extends BaseDto {
    private Long walletId;
    @NotBlank(message = "Name can't be blank")
    private String walletName;
    private Double balance;
    private WalletStatus status;
    private UserDto user;
    private List<TransactionDto> transaction;
}
