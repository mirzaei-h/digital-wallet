package com.digipay.wallet.models.dto;

import com.digipay.wallet.base.BaseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto extends BaseDto {
    private Long userId;
    private String username;
    private List<WalletDto> wallets;
    private List<TransactionDto> transactions;
}
